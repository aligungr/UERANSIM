package tr.havelsan.ueransim.core;

import tr.havelsan.ueransim.api.gnb.GnbInterfaceManagement;
import tr.havelsan.ueransim.events.GnbEvent;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

public class GNodeB {

    public static void run(GnbSimContext ctx) {
        new Thread(() -> {
            Logging.debug(Tag.FLOWS, "GNodeB has started: %s", ctx.config.gnbId);
            while (true) {
                while (cycle(ctx)) {

                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
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

    private static boolean cycle(GnbSimContext ctx) {
        var event = popEvent(ctx);
        if (event == null) {
            return false;
        }

        switch (event.cmd) {
            case "ngsetup":
                GnbInterfaceManagement.sendNgSetupRequest(ctx);
                return true;
        }

        return false;
    }
}
