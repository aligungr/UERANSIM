/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.nas;

import tr.havelsan.ueransim.app.common.contexts.NasContext;
import tr.havelsan.ueransim.app.common.nts.IwUplinkNas;
import tr.havelsan.ueransim.app.ue.nas.mm.MobilityManagement;
import tr.havelsan.ueransim.app.ue.nas.sec.NasSecurity;
import tr.havelsan.ueransim.app.ue.nas.sm.SessionManagement;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class NasTransport {

    public static void sendNas(NasContext ctx, NasMessage message) {
        var securedNas = NasSecurity.encryptNasMessage(ctx.currentNsCtx, message);
        var securedNasPdu = NasEncoder.nasPduS(securedNas);

        Log.debug(Tag.MSG, "Plain NAS as JSON: %s", Json.toJson(message));
        Log.debug(Tag.MSG, "Plain NAS PDU: %s", NasEncoder.nasPduS(message));
        Log.debug(Tag.MSG, "Secured NAS as JSON %s", Json.toJson(securedNas));
        Log.debug(Tag.MSG, "Secured NAS PDU: %s", securedNasPdu);

        ctx.rrcTask.push(new IwUplinkNas(ctx.ueCtx.ctxId, securedNasPdu));
        ctx.ueCtx.sim.triggerOnSend(ctx.ueCtx, message);
    }

    public static void receiveNas(NasContext ctx, NasMessage message) {
        Log.debug(Tag.MSG, "Secured NAS as JSON %s", Json.toJson(message));
        Log.debug(Tag.MSG, "Secured NAS PDU: %s", NasEncoder.nasPduS(message));

        message = NasSecurity.decryptNasMessage(ctx.currentNsCtx, message);

        Log.debug(Tag.MSG, "Plain NAS as JSON %s", Json.toJson(message));
        Log.debug(Tag.MSG, "Plain NAS PDU: %s", NasEncoder.nasPduS(message));

        ctx.ueCtx.sim.triggerOnReceive(ctx.ueCtx, message);

        if (message != null) {
            if (message instanceof PlainMmMessage) {
                MobilityManagement.receiveMm(ctx, (PlainMmMessage) message);
            } else {
                SessionManagement.receiveSm(ctx, (PlainSmMessage) message);
            }
        }
    }
}
