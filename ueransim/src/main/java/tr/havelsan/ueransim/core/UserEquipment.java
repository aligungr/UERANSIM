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

    public static void pushEvent(UeSimContext ctx, UeEvent cmd) {
        synchronized (ctx) {
            ctx.commandQueue.add(cmd);
        }
    }

    private static UeEvent popEvent(UeSimContext ctx) {
        synchronized (ctx) {
            return ctx.commandQueue.poll();
        }
    }

    private static void cycle(UeSimContext ctx) {

    }
}
