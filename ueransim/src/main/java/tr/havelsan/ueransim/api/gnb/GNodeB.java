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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.api.sys.MockedRadio;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.events.gnb.GnbCommandEvent;
import tr.havelsan.ueransim.events.gnb.GnbUplinkNasEvent;
import tr.havelsan.ueransim.events.gnb.SctpAssociationSetupEvent;
import tr.havelsan.ueransim.events.gnb.SctpReceiveEvent;
import tr.havelsan.ueransim.exceptions.NgapErrorException;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UserLocationInformation;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.msg.*;
import tr.havelsan.ueransim.ngap0.pdu.NGAP_PDU;
import tr.havelsan.ueransim.ngap1.NgapUtils;
import tr.havelsan.ueransim.structs.Guami;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;

import java.util.UUID;

public class GNodeB {

    public static void sendToNetworkNonUe(GnbSimContext ctx, Guami associatedAmf, NGAP_BaseMessage message) {
        Debugging.assertThread(ctx);

        var ngapPdu = message.buildPdu();

        Logging.debug(Tag.MESSAGING, "Sending NGAP: %s", message.getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var amfCtx = ctx.amfContexts.get(associatedAmf);

        if (amfCtx.sctpClient.isOpen()) {
            amfCtx.sctpClient.send(amfCtx.streamNumber, NgapEncoding.encodeAper(ngapPdu));
        } else {
            Logging.error(Tag.CONNECTION, "SCTP Connection could not established yet, message could not send.");
        }

        Logging.debug(Tag.MESSAGING, "Sent.");
    }

    public static void sendToNetworkUeAssociated(GnbSimContext ctx, UUID ueId, NGAP_BaseMessage message) {
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
                ie.userLocationInformationNR = NgapUtils.createUserLocationInformationNr(MockedRadio.findLocationOfUe(ctx.simCtx, ueId));
                message.addProtocolIe(ie);
            }
        }

        var ngapPdu = message.buildPdu();

        Logging.debug(Tag.MESSAGING, "Sending NGAP: %s", message.getClass().getSimpleName());
        Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        var amfCtx = ctx.amfContexts.get(ueCtx.associatedAmf);

        if (amfCtx.sctpClient.isOpen()) {
            amfCtx.sctpClient.send(amfCtx.streamNumber, NgapEncoding.encodeAper(ngapPdu));
        } else {
            Logging.error(Tag.CONNECTION, "SCTP Connection could not established yet, message could not send.");
        }

        Logging.debug(Tag.MESSAGING, "Sent.");
    }

    public static void receiveFromNetwork(GnbSimContext ctx, Guami associatedAmf, NGAP_PDU ngapPdu) {
        Debugging.assertThread(ctx);

        var ngapMessage = Ngap.getMessageFromPdu(ngapPdu);
        if (ngapMessage == null) {
            return;
        }

        try {
            if (ngapMessage instanceof NGAP_NGSetupResponse) {
                GnbInterfaceManagement.receiveNgSetupResponse(ctx, (NGAP_NGSetupResponse) ngapMessage);
            } else if (ngapMessage instanceof NGAP_NGSetupFailure) {
                GnbInterfaceManagement.receiveNgSetupFailure(ctx, (NGAP_NGSetupFailure) ngapMessage);
            } else if (ngapMessage instanceof NGAP_DownlinkNASTransport) {
                GnbNasTransport.receiveDownlinkNasTransport(ctx, (NGAP_DownlinkNASTransport) ngapMessage);
            } else if (ngapMessage instanceof NGAP_InitialContextSetupRequest) {
                GnbUeContextManagement.receiveInitialContextSetup(ctx, (NGAP_InitialContextSetupRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_RerouteNASRequest) {
                GnbNasTransport.receiveRerouteNasRequest(ctx, associatedAmf, (NGAP_RerouteNASRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_UEContextReleaseCommand) {
                GnbUeContextManagement.receiveContextReleaseCommand(ctx, (NGAP_UEContextReleaseCommand) ngapMessage);
            } else if (ngapMessage instanceof NGAP_UEContextModificationRequest) {
                GnbUeContextManagement.receiveContextModificationRequest(ctx, (NGAP_UEContextModificationRequest) ngapMessage);
            } else if (ngapMessage instanceof NGAP_PDUSessionResourceSetupRequest) {
                GnbPduSessionManagement.receiveResourceSetupRequest(ctx, (NGAP_PDUSessionResourceSetupRequest) ngapMessage);
            } else {
                Logging.error(Tag.MESSAGING, "Unhandled message received: %s", ngapMessage.getClass().getSimpleName());
            }
        } catch (NgapErrorException e) {
            var errorIndication = new NGAP_ErrorIndication();
            errorIndication.addProtocolIe(e.cause);
            if (e.associatedUe != null) {
                GNodeB.sendToNetworkUeAssociated(ctx, e.associatedUe, errorIndication);
            } else {
                GNodeB.sendToNetworkNonUe(ctx, associatedAmf, errorIndication);
            }
        }
    }

    public static void cycle(GnbSimContext ctx) {
        Debugging.assertThread(ctx);

        var event = ctx.popEvent();
        if (event instanceof SctpReceiveEvent) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);

            var ngapPdu = ((SctpReceiveEvent) event).ngapPdu;
            Logging.debug(Tag.MESSAGING, "Received NGAP: %s", ngapPdu.getClass().getSimpleName());
            Logging.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

            GNodeB.receiveFromNetwork(ctx, ((SctpReceiveEvent) event).associatedAmf, ngapPdu);
        } else if (event instanceof SctpAssociationSetupEvent) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);

            GnbInterfaceManagement.sendNgSetupRequest(ctx, ((SctpAssociationSetupEvent) event).guami);
        } else if (event instanceof GnbCommandEvent) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);

            var cmd = ((GnbCommandEvent) event).cmd;
            switch (cmd) {
                default:
                    Logging.error(Tag.EVENT, "GnbCommandEvent not recognized: %s", cmd);
                    break;
            }
        } else if (event instanceof GnbUplinkNasEvent) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);

            var e = (GnbUplinkNasEvent) event;
            GnbNasTransport.receiveUplinkNasTransport(ctx, e.ue, NasDecoder.nasPdu(e.nasPdu));
        }
    }
}
