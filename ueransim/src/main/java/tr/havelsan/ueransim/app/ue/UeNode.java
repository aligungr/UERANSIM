/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.app.UeAppTask;
import tr.havelsan.ueransim.app.ue.mr.UeMrTask;
import tr.havelsan.ueransim.app.ue.nas.NasTask;
import tr.havelsan.ueransim.app.ue.rrc.UeRrcTask;
import tr.havelsan.ueransim.app.utils.ConfigUtils;
import tr.havelsan.ueransim.nts.NtsId;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.console.Log;

public class UeNode {

    public static UeSimContext createContext(UeRanSim sim, UeConfig config) {
        var ctx = new UeSimContext(sim, "ue-" + config.supi.value);
        ctx.ueConfig = config;
        ctx.logger = ConfigUtils.createLoggerFor(ctx.nodeName);
        return ctx;
    }

    public static void run(UeSimContext ctx) {
        var tasks = new NtsTask[]{
                new UeMrTask(ctx),
                new NasTask(ctx),
                new UeRrcTask(ctx),
                new UeAppTask(ctx)
        };

        ctx.nts.registerTask(NtsId.UE_TASK_MR, tasks[0]);
        ctx.nts.registerTask(NtsId.UE_TASK_NAS, tasks[1]);
        ctx.nts.registerTask(NtsId.UE_TASK_RRC, tasks[2]);
        ctx.nts.registerTask(NtsId.UE_TASK_APP, tasks[3]);

        for (var task : tasks) Log.registerLogger(task.getThread(), ctx.logger);
        for (var task : tasks) task.start();
    }
}
