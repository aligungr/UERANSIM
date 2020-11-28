/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkNas;
import tr.havelsan.ueransim.app.common.itms.IwUplinkRrc;
import tr.havelsan.ueransim.rrc.core.RrcMessage;
import tr.havelsan.ueransim.rrc.messages.RrcDLInformationTransfer;

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
}
