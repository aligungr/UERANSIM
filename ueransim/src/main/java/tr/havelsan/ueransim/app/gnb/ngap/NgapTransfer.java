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

package tr.havelsan.ueransim.app.gnb.ngap;

import tr.havelsan.ueransim.app.app.Simulation;
import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.itms.IwNgapSend;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.gnb.utils.NgapUtils;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.NgapXerEncoder;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UserLocationInformation;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_RAN_UE_NGAP_ID;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class NgapTransfer {

    public static void sendNgapNonUe(GnbSimContext ctx, Guami associatedAmf, NGAP_BaseMessage message) {
        var ngapPdu = message.buildPdu();

        Log.debug(Tag.MESSAGING, "Sending NGAP: %s", message.getClass().getSimpleName());
        Log.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        ctx.itms.sendMessage(ItmsId.GNB_TASK_SCTP, new IwNgapSend(0, NgapEncoding.encodeAper(ngapPdu), associatedAmf));

        Simulation.triggerOnSend(ctx, message);
    }

    public static void sendNgapUeAssociated(GnbSimContext ctx, UUID ueId, NGAP_BaseMessage message) {
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

        Log.debug(Tag.MESSAGING, "Sending NGAP: %s", message.getClass().getSimpleName());
        Log.debug(Tag.MESSAGING, Utils.xmlToJson(NgapXerEncoder.encode(ngapPdu)));

        ctx.itms.sendMessage(ItmsId.GNB_TASK_SCTP, new IwNgapSend(ueCtx.uplinkStream, NgapEncoding.encodeAper(ngapPdu), ueCtx.associatedAmf));

        Simulation.triggerOnSend(ctx, message);
    }
}
