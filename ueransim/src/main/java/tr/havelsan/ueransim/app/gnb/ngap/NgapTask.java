/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.contexts.NgapAmfContext;
import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.exceptions.NgapErrorException;
import tr.havelsan.ueransim.app.common.nts.*;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UE_NGAP_IDs;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseProtocol;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

public class NgapTask extends NtsTask {

    private final NgapGnbContext ctx;

    public NgapTask(GnbSimContext gnbCtx) {
        this.ctx = new NgapGnbContext(gnbCtx);
    }

    @Override
    protected void main() {
        ctx.sctpTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_SCTP);
        ctx.rrcTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_RRC);
        ctx.gtpTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_GTP);
        ctx.appTask = ctx.gnbCtx.nts.findTask(NtsId.GNB_TASK_APP);

        for (var amfConfig : ctx.gnbCtx.config.amfConfigs) {
            var amfCtx = new NgapAmfContext();
            amfCtx.host = amfConfig.ngapIp;
            amfCtx.port = amfConfig.ngapPort;

            ctx.amfContexts.put(amfCtx.ctxId, amfCtx);
        }

        if (ctx.amfContexts.isEmpty()) {
            Log.error(Tag.CONFIG, "AMF contexts in GNB{%s} is empty", ctx.gnbCtx.ctxId);
            return;
        }

        for (var amfCtx : ctx.amfContexts.values()) {
            ctx.sctpTask.push(new IwSctpConnectionRequest(amfCtx.ctxId, amfCtx.host, amfCtx.port));
            ctx.waitingSctpClients++;
        }

        while (true) {
            var msg = take();
            if (msg instanceof IwNgapReceive) {
                receiveNgap((IwNgapReceive) msg);
            } else if (msg instanceof IwUplinkNas) {
                receiveUplinkNas((IwUplinkNas) msg);
            } else if (msg instanceof IwSctpAssociationSetup) {
                receiveAssociationSetup((IwSctpAssociationSetup) msg);
            }
        }
    }

    private void receiveNgap(IwNgapReceive msg) {
        var associatedAmf = msg.associatedAmf;
        var stream = msg.stream;
        var ngapPdu = msg.ngapPdu;

        Log.debug(Tag.MSG, "Received NGAP: %s", ngapPdu.getClass().getSimpleName());
        Log.debug(Tag.MSG, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var ngapMessage = Ngap.getMessageFromPdu(ngapPdu);
        if (ngapMessage == null) {
            return;
        }

        ctx.gnbCtx.sim.triggerOnReceive(ctx.gnbCtx, ngapMessage);

        try {
            if (!ctx.gnbCtx.config.ignoreStreamIds) {
                NGAP_Value ie = ngapMessage.getProtocolIe(NGAP_UE_NGAP_IDs.class);
                if (ie != null) {
                    if (stream == 0) {
                        Log.error(Tag.CONN, "received stream number == 0 in UE-associated signalling");
                        throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                    }
                    var ueCtx = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeForUeNgapIds(ctx, ngapMessage));
                    if (ueCtx.downlinkStream == 0) {
                        ueCtx.downlinkStream = stream;
                    } else if (ueCtx.downlinkStream != stream) {
                        Log.error(Tag.CONN, "received stream number is inconsistent. received %d, expected :%d", stream, ueCtx.downlinkStream);
                        throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                    }
                } else {
                    ie = ngapMessage.getProtocolIe(NGAP_RAN_UE_NGAP_ID.class);
                    if (ie != null) {
                        if (stream == 0) {
                            Log.error(Tag.CONN, "received stream number == 0 in UE-associated signalling");
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                        var ueCtx = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeIdDefault(ctx, ngapMessage));
                        if (ueCtx.downlinkStream == 0) {
                            ueCtx.downlinkStream = stream;
                        } else if (ueCtx.downlinkStream != stream) {
                            Log.error(Tag.CONN, "received stream number is inconsistent. received %d, expected :%d", stream, ueCtx.downlinkStream);
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                    } else {
                        if (stream != 0) {
                            Log.error(Tag.CONN, "received stream number != 0 in non-UE-associated signalling");
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                    }
                }
            }

            if (ngapMessage instanceof NGAP_NGSetupResponse) {
                NgapInterfaceManagement.receiveNgSetupResponse(ctx, associatedAmf, (NGAP_NGSetupResponse) ngapMessage);
            } else if (ngapMessage instanceof NGAP_NGSetupFailure) {
                NgapInterfaceManagement.receiveNgSetupFailure(ctx, associatedAmf, (NGAP_NGSetupFailure) ngapMessage);
            } else if (ngapMessage instanceof NGAP_DownlinkNASTransport) {
                NgapNasTransport.receiveDownlinkNasTransport(ctx, (NGAP_DownlinkNASTransport) ngapMessage);
            } else if (ngapMessage instanceof NGAP_InitialContextSetupRequest) {
                NgapUeContextManagement.receiveInitialContextSetup(ctx, (NGAP_InitialContextSetupRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_RerouteNASRequest) {
                NgapNasTransport.receiveRerouteNasRequest(ctx, associatedAmf, (NGAP_RerouteNASRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_UEContextReleaseCommand) {
                NgapUeContextManagement.receiveContextReleaseCommand(ctx, (NGAP_UEContextReleaseCommand) ngapMessage);
            } else if (ngapMessage instanceof NGAP_UEContextModificationRequest) {
                NgapUeContextManagement.receiveContextModificationRequest(ctx, (NGAP_UEContextModificationRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_PDUSessionResourceSetupRequest) {
                NgapPduSessionManagement.receiveResourceSetupRequest(ctx, (NGAP_PDUSessionResourceSetupRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_ErrorIndication) {
                NgapInterfaceManagement.receiveErrorIndication(ctx, (NGAP_ErrorIndication) ngapMessage);
            } else {
                Log.error(Tag.MSG, "Unhandled message received: %s", ngapMessage.getClass().getSimpleName());
            }
        } catch (NgapErrorException e) {
            var errorIndication = new NGAP_ErrorIndication();
            var ngapCause = new NGAP_Cause();
            ngapCause.setPresentValue(e.cause);

            errorIndication.addProtocolIe(ngapCause);
            if (e.associatedUe != null) {
                NgapTransfer.sendNgapUeAssociated(ctx, e.associatedUe, errorIndication);
            } else {
                NgapTransfer.sendNgapNonUe(ctx, associatedAmf, errorIndication);
            }
        }
    }

    private void receiveUplinkNas(IwUplinkNas msg) {
        if (!ctx.ueContexts.containsKey(msg.ue)) {
            NgapNasTransport.receiveInitialNasTransport(ctx, msg.ue, NasDecoder.nasPdu(msg.nasPdu));
        } else {
            NgapNasTransport.receiveUplinkNasTransport(ctx, msg.ue, NasDecoder.nasPdu(msg.nasPdu));
        }
    }

    private void receiveAssociationSetup(IwSctpAssociationSetup msg) {
        ctx.amfContexts.get(msg.amfId).association = msg.association;

        ctx.waitingSctpClients--;
        if (ctx.waitingSctpClients == 0) {
            var statusUpdate = new IwGnbStatusUpdate(IwGnbStatusUpdate.INITIAL_SCTP_ESTABLISHED);
            statusUpdate.isInitialSctpEstablished = true;
            ctx.appTask.push(statusUpdate);
        }

        NgapInterfaceManagement.sendNgSetupRequest(ctx, msg.amfId);
    }
}
