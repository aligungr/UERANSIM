/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.itms.IwUplinkRrc;
import tr.havelsan.ueransim.rrc.core.RrcMessage;
import tr.havelsan.ueransim.rrc.inners.RRC_ULInformationTransfer_CriticalExtensions;
import tr.havelsan.ueransim.rrc.messages.RrcDLInformationTransfer;
import tr.havelsan.ueransim.rrc.messages.RrcULInformationTransfer;
import tr.havelsan.ueransim.rrc.octetstrings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.sequences.RRC_ULInformationTransfer_IEs;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RrcTransport {

    public static void receiveRrcMessage(UeRrcContext ctx, RrcMessage message) {
        if (message instanceof RrcDLInformationTransfer) {
            var nasPdu = ((RrcDLInformationTransfer) message).criticalExtensions
                    .dlInformationTransfer.dedicatedNAS_Message.value;
            ctx.nasTask.push(new IwDownlinkNas(ctx.ueCtx.ctxId, nasPdu));
        }
    }

    public static void sendRrcMessage(UeRrcContext ctx, RrcMessage message) {
        ctx.mrTask.push(new IwUplinkRrc(ctx.ueCtx.ctxId, message));
    }

    public static void deliverUplinkNas(UeRrcContext ctx, OctetString nasPdu) {
        var rrc = new RrcULInformationTransfer();
        rrc.criticalExtensions = new RRC_ULInformationTransfer_CriticalExtensions();
        rrc.criticalExtensions.ulInformationTransfer = new RRC_ULInformationTransfer_IEs();
        rrc.criticalExtensions.ulInformationTransfer.dedicatedNAS_Message = new RRC_DedicatedNAS_Message(nasPdu);

        RrcTransport.sendRrcMessage(ctx, rrc);
    }
}
