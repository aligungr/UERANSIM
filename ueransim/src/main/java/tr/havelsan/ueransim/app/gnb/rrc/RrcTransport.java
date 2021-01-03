/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.rrc;

import tr.havelsan.ueransim.app.common.contexts.GnbRrcContext;
import tr.havelsan.ueransim.app.common.contexts.GnbRrcUeContext;
import tr.havelsan.ueransim.app.common.nts.IwDownlinkRrc;
import tr.havelsan.ueransim.rrc.RrcMessage;

import java.util.UUID;

public class RrcTransport {

    public static void receiveRrcMessage(GnbRrcContext ctx, UUID ue, RrcMessage message) {
        var ueCtx = ctx.ueMap.get(ue);
        if (ueCtx == null) {
            // TODO:
            ueCtx = new GnbRrcUeContext(ue);
            ctx.ueMap.put(ue, ueCtx);

            // Log.error(Tag.FLOW, "UE context could not found in receiveRrcMessage");
            // return;
        }

        if (message.ulInformationTransfer != null) {
            RrcHandler.receiveUlInformationTransfer(ctx, ueCtx, message.ulInformationTransfer);
        } else if (message.rrcSetupRequest != null) {
            RrcHandler.receiveSetupRequest(ctx, ueCtx, message.rrcSetupRequest);
        }
    }

    public static void sendRrcMessage(GnbRrcContext ctx, UUID ue, RrcMessage message) {
        ctx.mrTask.push(new IwDownlinkRrc(ue, message));
    }
}
