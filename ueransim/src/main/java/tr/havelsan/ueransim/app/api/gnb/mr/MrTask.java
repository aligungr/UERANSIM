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

package tr.havelsan.ueransim.app.api.gnb.mr;

import tr.havelsan.ueransim.app.Simulation;
import tr.havelsan.ueransim.app.api.gnb.ngap.NgapNasTransport;
import tr.havelsan.ueransim.app.itms.Itms;
import tr.havelsan.ueransim.app.itms.ItmsId;
import tr.havelsan.ueransim.app.itms.ItmsTask;
import tr.havelsan.ueransim.app.itms.wrappers.ConnectionReleaseWrapper;
import tr.havelsan.ueransim.app.itms.wrappers.DownlinkNasWrapper;
import tr.havelsan.ueransim.app.itms.wrappers.UplinkDataWrapper;
import tr.havelsan.ueransim.app.itms.wrappers.UplinkNasWrapper;
import tr.havelsan.ueransim.app.structs.simctx.GnbSimContext;
import tr.havelsan.ueransim.nas.NasDecoder;

public class MrTask extends ItmsTask {

    private final GnbSimContext ctx;

    public MrTask(Itms itms, int taskId, GnbSimContext ctx) {
        super(itms, taskId);
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = itms.receiveMessage(this);
            if (msg instanceof UplinkNasWrapper) {
                var w = (UplinkNasWrapper) msg;
                NgapNasTransport.receiveUplinkNasTransport(ctx, w.ue, NasDecoder.nasPdu(w.nasPdu));
            } else if (msg instanceof DownlinkNasWrapper) {
                var w = (DownlinkNasWrapper) msg;
                // TODO
                Simulation.findUe(ctx.simCtx, w.ue).itms.sendMessage(ItmsId.UE_TASK_MR, new DownlinkNasWrapper(w.ue, w.nasPdu));
            } else if (msg instanceof ConnectionReleaseWrapper) {
                var w = (ConnectionReleaseWrapper) msg;
                // TODO
                Simulation.findUe(ctx.simCtx, w.ue).itms.sendMessage(ItmsId.UE_TASK_MR, new ConnectionReleaseWrapper(w.ue));
            } else if (msg instanceof UplinkDataWrapper) {
                itms.sendMessage(ItmsId.GNB_TASK_GTP, msg);
            }
        }
    }
}
