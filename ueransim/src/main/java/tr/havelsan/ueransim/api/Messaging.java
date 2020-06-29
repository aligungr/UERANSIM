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

package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.utils.FlowLogging;

public class Messaging {

    public static void send2(SimulationContext ctx, NgapBuilder ngapBuilder, NasMessage nasMessage) {
        // Adding NAS PDU (if any)
        if (nasMessage != null) {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addNasPdu(nasMessage, NgapCriticality.REJECT);
        }

        // Adding AMF-UE-NGAP-ID (if any)
        {
            Long amfUeNgapId = ctx.gnb.amfUeNgapId;
            if (amfUeNgapId != null) {
                // NOTE: criticality is hardcoded here, it may be changed
                ngapBuilder.addAmfUeNgapId(amfUeNgapId, NgapCriticality.IGNORE);
            }
        }

        // Adding RAN-UE-NGAP-ID
        {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addRanUeNgapId(ctx.gnb.ranUeNgapId, NgapCriticality.REJECT);
        }

        // Adding user location information
        {
            // NOTE: criticality is hardcoded here, it may be changed
            ngapBuilder.addUserLocationInformationNR(ctx.ue.ueConfig.userLocationInformationNr, NgapCriticality.IGNORE);
        }

        var ngapPdu = ngapBuilder.build();

        FlowLogging.logSendingMessage(ngapPdu);
        ctx.gnb.sctpClient.send(ctx.gnb.streamNumber, Ngap.perEncode(ngapPdu));
        FlowLogging.logSentMessage();
    }
}
