/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.exceptions.NgapErrorException;
import tr.havelsan.ueransim.app.common.itms.IwNgapReceive;
import tr.havelsan.ueransim.app.common.itms.IwSctpAssociationSetup;
import tr.havelsan.ueransim.app.common.itms.IwUplinkNas;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UE_NGAP_IDs;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseProtocol;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

public class NgapTask extends NtsTask {

    private final GnbSimContext gnbCtx;
    private final NgapGnbContext ctx;

    public NgapTask(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.ctx = gnbCtx.ngapCtx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = take();
            if (msg instanceof IwNgapReceive) {
                receiveNgap(((IwNgapReceive) msg).associatedAmf, ((IwNgapReceive) msg).stream, ((IwNgapReceive) msg).ngapPdu);
            } else if (msg instanceof IwUplinkNas) {
                var w = (IwUplinkNas) msg;
                NgapNasTransport.receiveUplinkNasTransport(ctx, w.ue, NasDecoder.nasPdu(w.nasPdu));
            } else if (msg instanceof IwSctpAssociationSetup) {
                NgapInterfaceManagement.sendNgSetupRequest(ctx, ((IwSctpAssociationSetup) msg).guami);
            }
        }
    }

    private void receiveNgap(Guami associatedAmf, int stream, NGAP_PDU ngapPdu) {
        Log.debug(Tag.MSG, "Received NGAP: %s", ngapPdu.getClass().getSimpleName());
        Log.debug(Tag.MSG, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var ngapMessage = Ngap.getMessageFromPdu(ngapPdu);
        if (ngapMessage == null) {
            return;
        }

        ctx.gnbCtx.sim.triggerOnReceive(gnbCtx, ngapMessage);

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
}
