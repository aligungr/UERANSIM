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

package tr.havelsan.ueransim.app.core;

import tr.havelsan.ueransim.app.api.ue.app.AppTask;
import tr.havelsan.ueransim.app.api.ue.mr.MrTask;
import tr.havelsan.ueransim.app.api.ue.nas.NasTask;
import tr.havelsan.ueransim.app.api.ue.timers.TimersTask;
import tr.havelsan.ueransim.app.core.UeSimContext;

public class UeNode {

    public static final int TASK_MR = 1;
    public static final int TASK_NAS = 2;
    public static final int TASK_NAS_TIMERS = 3; // todo no need for seperate task
    public static final int TASK_APP = 4;

    public static final boolean AUTO = false;

    public static void run(UeSimContext ctx) {
        var itms = ctx.itms;

        var timersTask = new TimersTask(itms, TASK_NAS_TIMERS, ctx);
        var mrTask = new MrTask(itms, TASK_MR, ctx);
        var nasTask = new NasTask(itms, TASK_NAS, ctx);
        var appTask = new AppTask(itms, TASK_APP, ctx);

        itms.createTask(timersTask);
        itms.createTask(mrTask);
        itms.createTask(nasTask);
        itms.createTask(appTask);

        itms.startTask(timersTask);
        itms.startTask(mrTask);
        itms.startTask(nasTask);
        itms.startTask(appTask);
    }
}
