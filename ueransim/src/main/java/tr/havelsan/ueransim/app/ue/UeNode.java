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

package tr.havelsan.ueransim.app.ue;

import tr.havelsan.ueransim.app.app.AppConfig;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.app.UeAppTask;
import tr.havelsan.ueransim.app.ue.mr.MrTask;
import tr.havelsan.ueransim.app.ue.nas.NasTask;
import tr.havelsan.ueransim.app.ue.nas.NasTimersTask;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;
import tr.havelsan.ueransim.utils.console.Log;

public class UeNode {

    public static final boolean AUTO = false;

    public static void run(UeSimContext ctx) {
        ctx.logger = AppConfig.createLoggerFor(AppConfig.generateNodeName(ctx));

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
