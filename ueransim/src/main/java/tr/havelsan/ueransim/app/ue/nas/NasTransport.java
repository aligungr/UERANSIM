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

package tr.havelsan.ueransim.app.ue.nas;


import tr.havelsan.ueransim.app.app.Simulation;
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

        Log.debug(Tag.MESSAGING, "Plain NAS as JSON: %s", Json.toJson(message));
        Log.debug(Tag.MESSAGING, "Plain NAS PDU: %s", NasEncoder.nasPduS(message));
        Log.debug(Tag.MESSAGING, "Secured NAS as JSON %s", Json.toJson(securedNas));
        Log.debug(Tag.MESSAGING, "Secured NAS PDU: %s", securedNasPdu);

        ctx.itms.sendMessage(ItmsId.UE_TASK_MR, new IwUplinkNas(ctx.ctxId, securedNasPdu));

        Simulation.triggerOnSend(ctx, message);

        Log.funcOut();
    }

    public static void receiveNas(UeSimContext ctx, NasMessage message) {
        Log.funcIn("Receiving NAS message: %s", message.getClass().getSimpleName());

        Log.debug(Tag.MESSAGING, "Secured NAS as JSON %s", Json.toJson(message));
        Log.debug(Tag.MESSAGING, "Secured NAS PDU: %s", NasEncoder.nasPduS(message));

        message = NasSecurity.decryptNasMessage(ctx.currentNsCtx, message);

        Log.debug(Tag.MESSAGING, "Plain NAS as JSON %s", Json.toJson(message));
        Log.debug(Tag.MESSAGING, "Plain NAS PDU: %s", NasEncoder.nasPduS(message));

        Simulation.triggerOnReceive(ctx, message);

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
