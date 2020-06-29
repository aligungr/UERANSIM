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

package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.events.UeEvent;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class UserEquipment {

    public static void run(UeSimContext ctx) {
        new Thread(() -> {
            Logging.debug(Tag.FLOWS, "UE has started.");
            while (true) {
                cycle(ctx);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static void pushEvent(UeSimContext ctx, UeEvent event) {
        Logging.info(Tag.EVENT, "Pushed event: %s", event);
        synchronized (ctx) {
            ctx.eventQueue.add(event);
        }
    }

    private static UeEvent popEvent(UeSimContext ctx) {
        synchronized (ctx) {
            return ctx.eventQueue.poll();
        }
    }

    private static void cycle(UeSimContext ctx) {

    }
}
