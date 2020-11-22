/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.air;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.simctx.AirSimContext;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.console.Log;

public class AirNode {

    public static AirSimContext createContext(UeRanSim sim) {
        return new AirSimContext(sim, "air");
    }

    public static void run(AirSimContext ctx) {
        ctx.logger = ConfigUtils.createLoggerFor(ctx.nodeName);

        var tasks = new NtsTask[]{
                new TunBridgeTask(ctx),
        };

        ctx.nts.registerTask(ItmsId.AIR_TASK_TB, tasks[0]);

        for (var task : tasks) Log.registerLogger(task.getThread(), ctx.logger);
        for (var task : tasks) task.start();
    }
}
