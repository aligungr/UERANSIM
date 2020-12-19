/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.nts.nts.NtsTask;

public class GnbMrContext {
    public final GnbSimContext gnbCtx;

    public NtsTask gtpTask;
    public NtsTask rrcTask;

    public GnbMrContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
    }
}
