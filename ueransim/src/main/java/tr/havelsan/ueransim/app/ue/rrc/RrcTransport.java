/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.itms.IwUplinkRrc;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_ULInformationTransfer__criticalExtensions;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Value;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DLInformationTransfer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ULInformationTransfer;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ULInformationTransfer_IEs;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RrcTransport {

    public static void receiveRrcMessage(UeRrcContext ctx, RRC_Value message) {
        if (message instanceof RRC_DLInformationTransfer) {
            var nasPdu = ((RRC_DLInformationTransfer) message).criticalExtensions
                    .dlInformationTransfer.dedicatedNAS_Message.value;
            ctx.nasTask.push(new IwDownlinkNas(ctx.ueCtx.ctxId, nasPdu));
        }
    }

    public static void sendRrcMessage(UeRrcContext ctx, RRC_Value message) {
        ctx.mrTask.push(new IwUplinkRrc(ctx.ueCtx.ctxId, message));
    }

    public static void deliverUplinkNas(UeRrcContext ctx, OctetString nasPdu) {
        var rrc = new RRC_ULInformationTransfer();
        rrc.criticalExtensions = new RRC_ULInformationTransfer__criticalExtensions();
        rrc.criticalExtensions.ulInformationTransfer = new RRC_ULInformationTransfer_IEs();
        rrc.criticalExtensions.ulInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        RrcTransport.sendRrcMessage(ctx, rrc);
    }
}
