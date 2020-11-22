/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.nas;


import tr.havelsan.ueransim.app.common.itms.IwUplinkNas;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.mm.MobilityManagement;
import tr.havelsan.ueransim.app.ue.sm.SessionManagement;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NasTransport {

    public static void sendNas(UeSimContext ctx, NasMessage message) {
        Log.funcIn("Sending NAS message: %s", message.getClass().getSimpleName());

        var securedNas = NasSecurity.encryptNasMessage(ctx.currentNsCtx, message);
        var securedNasPdu = NasEncoder.nasPduS(securedNas);

        Log.debug(Tag.MSG, "Plain NAS as JSON: %s", Json.toJson(message));
        Log.debug(Tag.MSG, "Plain NAS PDU: %s", NasEncoder.nasPduS(message));
        Log.debug(Tag.MSG, "Secured NAS as JSON %s", Json.toJson(securedNas));
        Log.debug(Tag.MSG, "Secured NAS PDU: %s", securedNasPdu);

        ctx.nts.findTask(ItmsId.UE_TASK_MR).push(new IwUplinkNas(ctx.ctxId, securedNasPdu));
        ctx.sim.triggerOnSend(ctx, message);
        Log.funcOut();
    }

    public static void receiveNas(UeSimContext ctx, NasMessage message) {
        Log.funcIn("Receiving NAS message: %s", message.getClass().getSimpleName());

        Log.debug(Tag.MSG, "Secured NAS as JSON %s", Json.toJson(message));
        Log.debug(Tag.MSG, "Secured NAS PDU: %s", NasEncoder.nasPduS(message));

        message = NasSecurity.decryptNasMessage(ctx.currentNsCtx, message);

        Log.debug(Tag.MSG, "Plain NAS as JSON %s", Json.toJson(message));
        Log.debug(Tag.MSG, "Plain NAS PDU: %s", NasEncoder.nasPduS(message));

        ctx.sim.triggerOnReceive(ctx, message);

        if (message != null) {
            if (message instanceof PlainMmMessage) {
                MobilityManagement.receiveMm(ctx, (PlainMmMessage) message);
            } else {
                SessionManagement.receiveSm(ctx, (PlainSmMessage) message);
            }
        }

        Log.funcOut();
    }
}
