/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwRrcUplink;
import tr.havelsan.ueransim.rrc.core.RrcMessage;

public class RrcTransport {

    public static void sendRrcMessage(UeRrcContext ctx, RrcMessage message) {
        ctx.mrTask.push(new IwRrcUplink(message));
    }
}
