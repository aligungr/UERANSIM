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

package tr.havelsan.ueransim.app.api.ue;

import tr.havelsan.ueransim.app.api.ue.mm.MobilityManagement;
import tr.havelsan.ueransim.app.api.ue.sm.SessionManagement;
import tr.havelsan.ueransim.app.core.UeSimContext;
import tr.havelsan.ueransim.app.events.ue.UeCommandEvent;
import tr.havelsan.ueransim.app.events.ue.UeTimerExpireEvent;
import tr.havelsan.ueransim.app.testing.TestCommand;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Logging;

public class UserEquipment {

    public static boolean AUTO = false;

    public static void cycle(UeSimContext ctx) {
        MobilityManagement.cycle(ctx);

        var event = ctx.popEvent();
        if (event instanceof UeCommandEvent) {
            Logging.info(Tag.EVENT, "UeEvent is handling: %s", event);

            var cmd = ((UeCommandEvent) event).cmd;
            executeCommand(ctx, cmd);
        } else if (event instanceof UeTimerExpireEvent) {
            var timer = ((UeTimerExpireEvent) event).timer;
            Logging.info(Tag.NAS_TIMER, "NAS Timer expired: %s", timer);

            if (timer.isMmTimer) {
                MobilityManagement.receiveTimerExpire(ctx, timer);
            } else {
                SessionManagement.receiveTimerExpire(ctx, timer);
            }
        }
    }

    private static void executeCommand(UeSimContext ctx, TestCommand cmd) {
        if (!MobilityManagement.executeCommand(ctx, cmd)) {
            if (!SessionManagement.executeCommand(ctx, cmd)) {
                Logging.error(Tag.EVENT, "invalid command: %s", cmd);
            }
        }
    }
}
