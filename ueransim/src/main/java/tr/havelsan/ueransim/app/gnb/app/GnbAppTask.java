/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.gnb.app;

import tr.havelsan.ueransim.app.common.itms.IwInitialSctpReady;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.nts.NtsTask;

public class GnbAppTask extends NtsTask {

    private final GnbSimContext ctx;
    private boolean initialSctpReady;

    public GnbAppTask(GnbSimContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void main() {
        while (true) {
            var msg = take();
            if (msg instanceof IwInitialSctpReady) {
                initialSctpReady = true;
            }
        }
    }

    public boolean isInitialSctpReady() {
        return initialSctpReady;
    }
}
