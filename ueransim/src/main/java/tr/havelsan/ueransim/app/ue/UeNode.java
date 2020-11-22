/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.app.UeAppTask;
import tr.havelsan.ueransim.app.ue.mr.UeMrTask;
import tr.havelsan.ueransim.app.ue.nas.NasTask;
import tr.havelsan.ueransim.app.ue.nas.NasTimersTask;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;
import tr.havelsan.ueransim.utils.console.Log;

public class UeNode {

    // TODO: this should be inside of UeSimContext
    public static final boolean AUTO = false;

    public static UeSimContext createContext(UeRanSim sim, UeConfig config) {
        var ctx = new UeSimContext(sim, "ue-" + config.supi.value);
        ctx.ueConfig = config;
        return ctx;
    }

    public static void run(UeSimContext ctx) {
        ctx.logger = ConfigUtils.createLoggerFor(ctx.nodeName);

        var tasks = new NtsTask[]{
                new NasTimersTask(ctx),
                new UeMrTask(ctx),
                new NasTask(ctx),
                new UeAppTask(ctx)
        };

        ctx.nts.registerTask(ItmsId.UE_TASK_NAS_TIMERS, tasks[0]);
        ctx.nts.registerTask(ItmsId.UE_TASK_MR, tasks[1]);
        ctx.nts.registerTask(ItmsId.UE_TASK_NAS, tasks[2]);
        ctx.nts.registerTask(ItmsId.UE_TASK_APP, tasks[3]);

        for (var task : tasks) Log.registerLogger(task.getThread(), ctx.logger);
        for (var task : tasks) task.start();
    }
}
