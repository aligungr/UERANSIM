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

import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.events.gnb.GnbCommandEvent;
import tr.havelsan.ueransim.events.gnb.SctpReceiveEvent;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupFailure;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.NGSetupResponse;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapInternal;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.FlowLogging;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class GNodeB {

    public static void sendToNetwork(GnbSimContext ctx, NgapBuilder ngapBuilder) {
        Debugging.assertThread(ctx);

        sendToNetwork(ctx, ngapBuilder, null);
    }

    public static void sendToNetwork(GnbSimContext ctx, NgapBuilder ngapBuilder, NasMessage nasMessage) {
        Debugging.assertThread(ctx);

        // Adding NAS PDU (if any)
        if (nasMessage != null) {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addNasPdu(nasMessage, NgapCriticality.REJECT);
        }

        // Adding AMF-UE-NGAP-ID (if any)
        {
            Long amfUeNgapId = ctx.amfUeNgapId;
            if (amfUeNgapId != null) {
                // NOTE: criticality is hardcoded here, it may be changed
                ngapBuilder.addAmfUeNgapId(amfUeNgapId, NgapCriticality.IGNORE);
            }
        }

        // Adding RAN-UE-NGAP-ID
        {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addRanUeNgapId(ctx.ranUeNgapId, NgapCriticality.REJECT);
        }

        // Adding user location information
        {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addUserLocationInformationNR(ctx.simCtx.ue.ueConfig.userLocationInformationNr, NgapCriticality.IGNORE);
        }

        var ngapPdu = ngapBuilder.build();

        FlowLogging.logSendingMessage(ngapPdu);
        ctx.sctpClient.send(ctx.streamNumber, Ngap.perEncode(ngapPdu));
        FlowLogging.logSentMessage();
    }

    public static void receiveFromNetwork(GnbSimContext ctx, NGAP_PDU ngapPdu) {
        Debugging.assertThread(ctx);

        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);

        // check for AMF-UE-NGAP-ID
        {
            var ieAmfUeNgapId = NgapInternal.extractProtocolIe(ngapMessage, AMF_UE_NGAP_ID.class);
            if (ieAmfUeNgapId.size() > 0) {
                var ie = ieAmfUeNgapId.get(ieAmfUeNgapId.size() - 1);
                ctx.amfUeNgapId = ie.value;
            }
        }

        if (ngapMessage instanceof NGSetupResponse) {
            GnbInterfaceManagement.receiveNgSetupResponse(ctx, (NGSetupResponse) ngapMessage);
        } else if (ngapMessage instanceof NGSetupFailure) {
            GnbInterfaceManagement.receiveNgSetupFailure(ctx, (NGSetupFailure) ngapMessage);
        } else if (ngapMessage instanceof DownlinkNASTransport) {
            GnbNasTransport.handleDownlinkNasTransport(ctx, (DownlinkNASTransport) ngapMessage);
        } else if (ngapMessage instanceof InitialContextSetupRequest) {
            GnbUeContextManagement.handleInitialContextSetup(ctx, (InitialContextSetupRequest) ngapMessage);
        } else {
            FlowLogging.logUnhandledMessage(ngapMessage.getClass().getSimpleName());
        }
    }

    public static void cycle(GnbSimContext ctx) {
        Debugging.assertThread(ctx);

        var event = ctx.popEvent();
        if (event instanceof SctpReceiveEvent) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);

            var ngapPdu = ((SctpReceiveEvent) event).ngapPdu;
            FlowLogging.logReceivedMessage(ngapPdu);
            GNodeB.receiveFromNetwork(ctx, ngapPdu);
        } else if (event instanceof GnbCommandEvent) {
            Logging.info(Tag.EVENT, "GnbEvent is handling: %s", event);

            var cmd = ((GnbCommandEvent) event).cmd;
            switch (cmd) {
                case "ngsetup":
                    GnbInterfaceManagement.sendNgSetupRequest(ctx);
                    break;
                default:
                    Logging.error(Tag.EVENT, "GnbCommandEvent not recognized: %s", cmd);
                    break;
            }
        }
    }
}
