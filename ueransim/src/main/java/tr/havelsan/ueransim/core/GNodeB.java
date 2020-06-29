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

import tr.havelsan.ueransim.api.gnb.GnbInterfaceManagement;
import tr.havelsan.ueransim.api.gnb.GnbMessaging;
import tr.havelsan.ueransim.events.GnbCommandEvent;
import tr.havelsan.ueransim.events.GnbEvent;
import tr.havelsan.ueransim.events.SctpReceiveEvent;
import tr.havelsan.ueransim.utils.FlowLogging;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class GNodeB {

    public static void run(GnbSimContext ctx) {
        try {
            ctx.sctpClient.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            try {
                ctx.sctpClient.receiverLoop(receivedBytes -> pushEvent(ctx, new SctpReceiveEvent(receivedBytes)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            Logging.debug(Tag.FLOWS, "GNodeB has started: %s", ctx.config.gnbId);
            while (true) {
                while (hasEvent(ctx)) {
                    cycle(ctx);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static boolean hasEvent(GnbSimContext ctx) {
        synchronized (ctx) {
            return !ctx.eventQueue.isEmpty();
        }
    }

    public static void pushEvent(GnbSimContext ctx, GnbEvent event) {
        Logging.info(Tag.EVENT, "Pushed event: %s", event);
        synchronized (ctx) {
            ctx.eventQueue.add(event);
        }
    }

    private static GnbEvent popEvent(GnbSimContext ctx) {
        synchronized (ctx) {
            return ctx.eventQueue.poll();
        }
    }

    private static void cycle(GnbSimContext ctx) {
        var event = popEvent(ctx);
        if (event instanceof SctpReceiveEvent) {
            var ngapPdu = ((SctpReceiveEvent) event).ngapPdu;
            FlowLogging.logReceivedMessage(ngapPdu);
            GnbMessaging.receiveFromNetwork(ctx, ngapPdu);
        } else if (event instanceof GnbCommandEvent) {
            var cmd = ((GnbCommandEvent) event).cmd;
            switch (cmd) {
                case "ngsetup":
                    GnbInterfaceManagement.sendNgSetupRequest(ctx);
                    break;
            }
        }
    }
}
