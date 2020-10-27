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

package tr.havelsan.ueransim.app.ue.app;

import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.*;
import tr.havelsan.ueransim.itms.Itms;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.itms.ItmsTask;

public class UeAppTask extends ItmsTask {

    private final UeSimContext ctx;

    public UeAppTask(Itms itms, int taskId, UeSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = ctx.itms.receiveMessage(this);
            if (msg instanceof IwUeTestCommand) {
                var cmd = ((IwUeTestCommand) msg).cmd;

                if (cmd instanceof TestCmd_InitialRegistration) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_PeriodicRegistration) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_PduSessionEstablishment) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_Deregistration) {
                    ctx.itms.sendMessage(ItmsId.UE_TASK_NAS, msg);
                } else if (cmd instanceof TestCmd_Ping) {
                    // TODO
                }
            }
        }
    }
}
