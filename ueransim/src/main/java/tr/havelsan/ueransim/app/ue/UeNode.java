/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.app.UeAppTask;
import tr.havelsan.ueransim.app.ue.mr.MrTask;
import tr.havelsan.ueransim.app.ue.nas.NasTask;
import tr.havelsan.ueransim.app.ue.nas.NasTimersTask;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.utils.console.Log;

public class UeNode {

    public static final boolean AUTO = false;

    public static UeSimContext createContext(UeRanSim sim, UeConfig config) {
        var ctx = new UeSimContext(sim);
        ctx.ueConfig = config;
        return ctx;
    }

    public static void run(UeSimContext ctx) {
        ctx.logger = ConfigUtils.createLoggerFor(ConfigUtils.generateNodeName(ctx));

        var itms = ctx.itms;

        var tasks = new ItmsTask[]{
                new NasTimersTask(itms, ItmsId.UE_TASK_NAS_TIMERS, ctx),
                new MrTask(itms, ItmsId.UE_TASK_MR, ctx),
                new NasTask(itms, ItmsId.UE_TASK_NAS, ctx),
                new UeAppTask(itms, ItmsId.UE_TASK_APP, ctx)
        };

        for (var task : tasks) {
            Log.registerLogger(task.thread, ctx.logger);
        }
        for (var task : tasks) {
            itms.createTask(task);
        }
        for (var task : tasks) {
            itms.startTask(task);
        }
    }
}
