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

package tr.havelsan.ueransim.api.ue.sm;

import tr.havelsan.ueransim.api.nas.NasTimer;
import tr.havelsan.ueransim.api.ue.UserEquipment;
import tr.havelsan.ueransim.core.UeSimContext;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentAccept;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionEstablishmentReject;
import tr.havelsan.ueransim.nas.impl.messages.UlNasTransport;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class SessionManagement {

    public static void sendSm(UeSimContext ctx, UlNasTransport message) {
        Debugging.assertThread(ctx);

        UserEquipment.sendNas(ctx, message);
    }

    public static void receiveSm(UeSimContext ctx, PlainSmMessage message) {
        Debugging.assertThread(ctx);

        if (message instanceof PduSessionEstablishmentAccept) {
            SmPduSessionEstablishment.receiveEstablishmentAccept(ctx, (PduSessionEstablishmentAccept) message);
        } else if (message instanceof PduSessionEstablishmentReject) {
            SmPduSessionEstablishment.receiveEstablishmentReject(ctx, (PduSessionEstablishmentReject) message);
        } else {
            Logging.error(Tag.MESSAGING, "Unhandled message received: %s", message.getClass().getSimpleName());
        }
    }

    public static void receiveTimerExpire(UeSimContext ctx, NasTimer timer) {
        Debugging.assertThread(ctx);

        // todo
    }

    public static void executeCommand(UeSimContext ctx, String cmd) {
        Debugging.assertThread(ctx);

        switch (cmd) {
            case "pdu-session-establishment":
                SmPduSessionEstablishment.sendEstablishmentRequest(ctx);
                break;
            default:
                Logging.error(Tag.EVENT, "SessionManagement.executeCommand, command not recognized: %s", cmd);
                break;
        }
    }
}
