/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkRrc;
import tr.havelsan.ueransim.rrc.RrcMessage;

import java.util.UUID;

public class RrcTransport {

    public static void receiveRrcMessage(GnbRrcContext ctx, UUID ue, RrcMessage message) {
        if (message.ul_dcch != null) {
            var msg = message.ul_dcch.message.c1;
            if (msg.ulInformationTransfer != null) {
                var nasPdu = msg.ulInformationTransfer.criticalExtensions.ulInformationTransfer
                        .dedicatedNAS_Message.value;
                RrcNasTransport.deliverUlNas(ctx, ue, nasPdu);
            }
        }
    }

    public static void sendRrcMessage(GnbRrcContext ctx, UUID ue, RrcMessage message) {
        ctx.mrTask.push(new IwDownlinkRrc(ue, message));
    }
}
