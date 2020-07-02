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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api.ue;

import tr.havelsan.ueransim.api.nas.NasSecurity;
import tr.havelsan.ueransim.api.ue.mm.MobilityManagement;
import tr.havelsan.ueransim.api.ue.sm.SessionManagement;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.events.gnb.GnbUplinkNasEvent;
import tr.havelsan.ueransim.events.ue.UeCommandEvent;
import tr.havelsan.ueransim.events.ue.UeDownlinkNasEvent;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class UserEquipment {

    public static void sendNas(UeSimContext ctx, NasMessage message) {
        Debugging.assertThread(ctx);

        NasMessage securedNas = NasSecurity.encryptNasMessage(ctx.currentNsCtx, message);

        ctx.simCtx.gnb.pushEvent(new GnbUplinkNasEvent(ctx.simId, NasEncoder.nasPdu(securedNas)));
    }

    public static void receiveNas(UeSimContext ctx, NasMessage message) {
        Debugging.assertThread(ctx);

        message = NasSecurity.decryptNasMessage(ctx.currentNsCtx, message);

        if (message instanceof PlainMmMessage) {
            MobilityManagement.receiveMm(ctx, (PlainMmMessage) message);
        } else {
            SessionManagement.receiveSm(ctx, (PlainSmMessage) message);
        }
    }

    public static void cycle(UeSimContext ctx) {
        var event = ctx.popEvent();
        if (event instanceof UeCommandEvent) {
            Logging.info(Tag.EVENT, "UeEvent is handling: %s", event);

            var cmd = ((UeCommandEvent) event).cmd;
            switch (cmd) {
                case "initial-registration":
                    MobilityManagement.executeCommand(ctx, cmd);
                    break;
                default:
                    Logging.error(Tag.EVENT, "UeCommandEvent not recognized: %s", cmd);
                    break;
            }
        } else if (event instanceof UeDownlinkNasEvent) {
            Logging.info(Tag.EVENT, "UeEvent is handling: %s", event);

            receiveNas(ctx, NasDecoder.nasPdu(((UeDownlinkNasEvent) event).nasPdu));
        }
    }
}
