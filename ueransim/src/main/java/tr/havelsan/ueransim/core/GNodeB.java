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
