/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.app.api.gnb;

import tr.havelsan.ueransim.app.api.gnb.ngap.*;
import tr.havelsan.ueransim.app.api.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.app.api.sys.Simulation;
import tr.havelsan.ueransim.app.core.GnbSimContext;
import tr.havelsan.ueransim.app.events.gnb.GnbCommandEvent;
import tr.havelsan.ueransim.app.events.gnb.GnbUplinkNasEvent;
import tr.havelsan.ueransim.app.events.gnb.SctpAssociationSetupEvent;
import tr.havelsan.ueransim.app.events.gnb.SctpReceiveEvent;
import tr.havelsan.ueransim.app.exceptions.NgapErrorException;
import tr.havelsan.ueransim.app.structs.Guami;
import tr.havelsan.ueransim.app.utils.Debugging;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UE_NGAP_IDs;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UserLocationInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_CauseProtocol;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Logging;

import java.util.UUID;

public class GNodeB {

    public static void sendNgapNonUe(GnbSimContext ctx, Guami associatedAmf, NGAP_BaseMessage message) {
        Debugging.assertThread(ctx);

        var ngapPdu = message.buildPdu();

        Logging.debug(Tag.MESSAGING, "Sending NGAP: %s", message.getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var amfCtx = ctx.amfContexts.get(associatedAmf);

        if (amfCtx.sctpClient.isOpen()) {
            amfCtx.sctpClient.send(0, NgapEncoding.encodeAper(ngapPdu));
        } else {
            Logging.error(Tag.CONNECTION, "SCTP Connection could not established yet, message could not send.");
        }

        Logging.debug(Tag.MESSAGING, "Sent.");

        Simulation.triggerOnSend(ctx, message);
    }

    public static void sendNgapUeAssociated(GnbSimContext ctx, UUID ueId, NGAP_BaseMessage message) {
        Debugging.assertThread(ctx);

        // Find UE gNB context
        var ueCtx = ctx.ueContexts.get(ueId);

        // Adding AMF-UE-NGAP-ID (if any)
        {
            if (message.isProtocolIeUsable(NGAP_AMF_UE_NGAP_ID.class)) {
                Long amfUeNgapId = ueCtx.amfUeNgapId;
                if (amfUeNgapId != null) {
                    message.addProtocolIe(new NGAP_AMF_UE_NGAP_ID(amfUeNgapId));
                }
            }
        }

        // Adding RAN-UE-NGAP-ID
        {
            if (message.isProtocolIeUsable(NGAP_RAN_UE_NGAP_ID.class)) {
                message.addProtocolIe(new NGAP_RAN_UE_NGAP_ID(ueCtx.ranUeNgapId));
            }
        }

        // Adding user location information
        {
            if (message.isProtocolIeUsable(NGAP_UserLocationInformation.class)) {
                var ie = new NGAP_UserLocationInformation();
                ie.userLocationInformationNR = NgapUtils.createUserLocationInformationNr(ctx.config.gnbPlmn,
                        new VTrackingAreaIdentity(ctx.config.gnbPlmn, ctx.config.tac), ctx.config.nci);
                message.addProtocolIe(ie);
            }
        }

        var ngapPdu = message.buildPdu();

        Logging.debug(Tag.MESSAGING, "Sending NGAP: %s", message.getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var amfCtx = ctx.amfContexts.get(ueCtx.associatedAmf);

        if (amfCtx.sctpClient.isOpen()) {
            amfCtx.sctpClient.send(ueCtx.uplinkStream, NgapEncoding.encodeAper(ngapPdu));
        } else {
            Logging.error(Tag.CONNECTION, "SCTP Connection could not established yet, message could not send.");
        }

        Logging.debug(Tag.MESSAGING, "Sent.");

        Simulation.triggerOnSend(ctx, message);
    }

    public static void receiveNgap(GnbSimContext ctx, Guami associatedAmf, int stream, NGAP_PDU ngapPdu) {
        Debugging.assertThread(ctx);

        var ngapMessage = Ngap.getMessageFromPdu(ngapPdu);
        if (ngapMessage == null) {
            return;
        }

        Simulation.triggerOnReceive(ctx, ngapMessage);

        try {
            if (!ctx.config.ignoreStreamIds) {
                NGAP_Value ie = ngapMessage.getProtocolIe(NGAP_UE_NGAP_IDs.class);
                if (ie != null) {
                    if (stream == 0) {
                        Logging.error(Tag.CONNECTION, "received stream number == 0 in UE-associated signalling");
                        throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                    }
                    var ueCtx = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeForUeNgapIds(ctx, ngapMessage));
                    if (ueCtx.downlinkStream == 0) {
                        ueCtx.downlinkStream = stream;
                    } else if (ueCtx.downlinkStream != stream) {
                        Logging.error(Tag.CONNECTION, "received stream number is inconsistent. received %d, expected :%d", stream, ueCtx.downlinkStream);
                        throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                    }
                } else {
                    ie = ngapMessage.getProtocolIe(NGAP_RAN_UE_NGAP_ID.class);
                    if (ie != null) {
                        if (stream == 0) {
                            Logging.error(Tag.CONNECTION, "received stream number == 0 in UE-associated signalling");
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                        var ueCtx = ctx.ueContexts.get(NgapUeManagement.findAssociatedUeIdDefault(ctx, ngapMessage));
                        if (ueCtx.downlinkStream == 0) {
                            ueCtx.downlinkStream = stream;
                        } else if (ueCtx.downlinkStream != stream) {
                            Logging.error(Tag.CONNECTION, "received stream number is inconsistent. received %d, expected :%d", stream, ueCtx.downlinkStream);
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                    } else {
                        if (stream != 0) {
                            Logging.error(Tag.CONNECTION, "received stream number != 0 in non-UE-associated signalling");
                            throw new NgapErrorException(NGAP_CauseProtocol.UNSPECIFIED);
                        }
                    }
                }
            }

            if (ngapMessage instanceof NGAP_NGSetupResponse) {
                NgapInterfaceManagement.receiveNgSetupResponse(ctx, (NGAP_NGSetupResponse) ngapMessage);
            } else if (ngapMessage instanceof NGAP_NGSetupFailure) {
                NgapInterfaceManagement.receiveNgSetupFailure(ctx, (NGAP_NGSetupFailure) ngapMessage);
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
            } else {
                Logging.error(Tag.MESSAGING, "Unhandled message received: %s", ngapMessage.getClass().getSimpleName());
            }
        } catch (NgapErrorException e) {
            var errorIndication = new NGAP_ErrorIndication();
            var ngapCause = new NGAP_Cause();
            ngapCause.setPresentValue(e.cause);

            errorIndication.addProtocolIe(ngapCause);
            if (e.associatedUe != null) {
                GNodeB.sendNgapUeAssociated(ctx, e.associatedUe, errorIndication);
            } else {
                GNodeB.sendNgapNonUe(ctx, associatedAmf, errorIndication);
            }
        }
    }

    public static void cycle(GnbSimContext ctx) {
        Debugging.assertThread(ctx);

        var event = ctx.popEvent();
        if (event != null) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);
        }

        if (event instanceof SctpReceiveEvent) {
            var ngapPdu = ((SctpReceiveEvent) event).ngapPdu;
            Logging.debug(Tag.MESSAGING, "Received NGAP: %s", ngapPdu.getClass().getSimpleName());
            Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

            GNodeB.receiveNgap(ctx, ((SctpReceiveEvent) event).associatedAmf, ((SctpReceiveEvent) event).streamNumber, ngapPdu);
        } else if (event instanceof SctpAssociationSetupEvent) {
            var amfCtx = ctx.amfContexts.get(((SctpAssociationSetupEvent) event).guami);
            amfCtx.association = ((SctpAssociationSetupEvent) event).association;

            NgapInterfaceManagement.sendNgSetupRequest(ctx, amfCtx.guami);
        } else if (event instanceof GnbCommandEvent) {
            var cmd = ((GnbCommandEvent) event).cmd;
            switch (cmd) {
                default:
                    Logging.error(Tag.EVENT, "GnbCommandEvent not recognized: %s", cmd);
                    break;
            }
        } else if (event instanceof GnbUplinkNasEvent) {
            var e = (GnbUplinkNasEvent) event;
            NgapNasTransport.receiveUplinkNasTransport(ctx, e.ue, NasDecoder.nasPdu(e.nasPdu));
        }
    }
}
