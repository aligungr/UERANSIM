/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.app;

import tr.havelsan.ueransim.app.common.info.GnbStatusInfo;
import tr.havelsan.ueransim.app.common.nts.IwGnbStatusInfoRequest;
import tr.havelsan.ueransim.app.common.nts.IwGnbStatusUpdate;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.nts.nts.NtsTask;

public class GnbAppTask extends NtsTask {

    private final GnbSimContext ctx;
    private final GnbStatusInfo statusInfo;

    public GnbAppTask(GnbSimContext ctx) {
        this.ctx = ctx;
        this.statusInfo = new GnbStatusInfo();
    }

    @Override
    protected void main() {
        while (true) {
            var msg = take();
            if (msg instanceof IwGnbStatusUpdate) {
                receiveStatusUpdate((IwGnbStatusUpdate) msg);
            } else if (msg instanceof IwGnbStatusInfoRequest) {
                var requester = ((IwGnbStatusInfoRequest) msg).requester;
                var consumer = ((IwGnbStatusInfoRequest) msg).consumerFunc;
                requester.push(() -> consumer.accept(createStatusInfo()));
            }
        }
    }

    private void receiveStatusUpdate(IwGnbStatusUpdate message) {
        switch (message.what) {
            case IwGnbStatusUpdate.INITIAL_SCTP_ESTABLISHED:
                statusInfo.isInitialSctpEstablished = message.isInitialSctpEstablished;
                break;
        }
    }

    private GnbStatusInfo createStatusInfo() {
        var inf = new GnbStatusInfo();
        inf.isInitialSctpEstablished = statusInfo.isInitialSctpEstablished;
        return inf;
    }

    @Deprecated // TODO
    public boolean isInitialSctpReady() {
        return statusInfo.isInitialSctpEstablished;
    }
}
