/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.rrc;

import tr.havelsan.ueransim.app.common.contexts.UeRrcContext;
import tr.havelsan.ueransim.app.common.itms.IwDownlinkRrc;
import tr.havelsan.ueransim.app.common.itms.IwPlmnSearchRequest;
import tr.havelsan.ueransim.app.common.itms.IwPlmnSearchResponse;
import tr.havelsan.ueransim.app.common.itms.IwUplinkNas;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.itms.NtsId;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class UeRrcTask extends NtsTask {

    private final UeRrcContext ctx;

    public UeRrcTask(UeSimContext ueCtx) {
        this.ctx = new UeRrcContext(ueCtx);
    }

    @Override
    public void main() {
        ctx.mrTask = ctx.ueCtx.nts.findTask(NtsId.UE_TASK_MR);
        ctx.nasTask = ctx.ueCtx.nts.findTask(NtsId.UE_TASK_NAS);

        while (true) {
            var msg = take();
            if (msg instanceof IwDownlinkRrc) {
                receiveDownlinkRrc((IwDownlinkRrc) msg);
            } else if (msg instanceof IwUplinkNas) {
                receiveUplinkNas((IwUplinkNas) msg);
            } else if (msg instanceof IwPlmnSearchRequest) {
                receivePlmnSearchRequest((IwPlmnSearchRequest) msg);
            } else if (msg instanceof IwPlmnSearchResponse) {
                receivePlmnSearchResponse((IwPlmnSearchResponse) msg);
            }
        }
    }

    private void receiveDownlinkRrc(IwDownlinkRrc msg) {
        RrcTransport.receiveRrcMessage(ctx, msg.rrcMessage);
    }

    private void receiveUplinkNas(IwUplinkNas msg) {
        RrcTransport.deliverUplinkNas(ctx, msg.nasPdu);
    }

    private void receivePlmnSearchRequest(IwPlmnSearchRequest msg) {
        ctx.mrTask.push(msg);
    }

    private void receivePlmnSearchResponse(IwPlmnSearchResponse msg) {
        ctx.nasTask.push(msg);
    }
}
