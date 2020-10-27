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

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap0.Ngap;
import tr.havelsan.ueransim.ngap0.NgapDataUnitType;
import tr.havelsan.ueransim.ngap0.NgapEncoding;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_AMFSetID;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NGAP_Message;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AllowedNSSAI;
import tr.havelsan.ueransim.ngap0.msg.NGAP_DownlinkNASTransport;
import tr.havelsan.ueransim.ngap0.msg.NGAP_InitialUEMessage;
import tr.havelsan.ueransim.ngap0.msg.NGAP_RerouteNASRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_UplinkNASTransport;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit10;
import tr.havelsan.ueransim.utils.console.Log;

import java.util.UUID;

public class NgapNasTransport {

    public static void receiveUplinkNasTransport(GnbSimContext ctx, UUID associatedUe, NasMessage nasMessage) {
        Log.funcIn("Handling Uplink NAS Transport");

        NGAP_BaseMessage ngap;
        if (ctx.ueContexts.containsKey(associatedUe)) {
            ngap = new NGAP_UplinkNASTransport();
        } else {
            NgapUeManagement.createUeContext(ctx, associatedUe);

            ngap = new NGAP_InitialUEMessage();
            ngap.addProtocolIe(NGAP_RRCEstablishmentCause.MO_DATA);

            var ueCtx = ctx.ueContexts.get(associatedUe);
            var amfCtx = ctx.amfContexts.get(ueCtx.associatedAmf);

            amfCtx.nextStream = (amfCtx.nextStream + 1) % amfCtx.association.outbound;
            if ((amfCtx.nextStream == 0) && (amfCtx.association.outbound > 1)) {
                amfCtx.nextStream += 1;
            }
            ueCtx.uplinkStream = amfCtx.nextStream;
        }

        if (nasMessage != null) {
            ngap.addProtocolIe(new NGAP_NAS_PDU(NasEncoder.nasPdu(nasMessage)));
        }

        NgapTransfer.sendNgapUeAssociated(ctx, associatedUe, ngap);

        Log.funcOut();
    }

    public static void receiveDownlinkNasTransport(GnbSimContext ctx, NGAP_DownlinkNASTransport message) {
        Log.funcIn("Handling Downlink NAS Transport");

        var associatedUe = NgapUeManagement.findAssociatedUeIdDefault(ctx, message);

        var nasMessage = message.getNasMessage();
        if (nasMessage != null) {
            ctx.itms.sendMessage(ItmsId.GNB_TASK_MR, new IwDownlinkNas(associatedUe, NasEncoder.nasPduS(nasMessage)));
        }

        Log.funcOut();
    }

    public static void receiveRerouteNasRequest(GnbSimContext ctx, Guami associatedAmf, NGAP_RerouteNASRequest message) {
        Log.funcIn("Handling Reroute NAS Request");

        var associatedUe = NgapUeManagement.findAssociatedUeIdDefault(ctx, message);

        var ngapMessage = message.getProtocolIe(NGAP_NGAP_Message.class);
        var amfSetId = message.getProtocolIe(NGAP_AMFSetID.class);
        var allowedNssai = message.getProtocolIe(NGAP_AllowedNSSAI.class);

        var initialUeMessage = (NGAP_InitialUEMessage) NgapEncoding.decodeAper(ngapMessage.value, NgapDataUnitType.InitialUEMessage);

        var newMessage = Ngap.deepClone(initialUeMessage);
        if (allowedNssai != null) {
            newMessage.addProtocolIe(allowedNssai);
        }

        var newAmf = NgapUeManagement.selectNewAmfForReAllocation(ctx, associatedAmf, new Bit10(amfSetId.value.intValue()));

        if (newAmf != null) {
            Log.info(Tag.PROC, "New AMF selected for re-allocation. AMF: %s", newAmf);

            var ueCtx = ctx.ueContexts.get(associatedUe);
            ueCtx.associatedAmf = newAmf;

            NgapTransfer.sendNgapUeAssociated(ctx, associatedUe, newMessage);
        } else {
            Log.error(Tag.PROC, "AMF selection for re-allocation failed. Could not find a suitable AMF.");
        }

        Log.funcOut();
    }
}
