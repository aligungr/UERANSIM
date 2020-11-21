/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.trigger;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public abstract class TriggeringWrapper {
    public final BaseSimContext ctx;

    public TriggeringWrapper(BaseSimContext ctx) {
        this.ctx = ctx;
    }
}
