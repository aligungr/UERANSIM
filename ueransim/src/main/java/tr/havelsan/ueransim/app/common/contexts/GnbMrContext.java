/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
