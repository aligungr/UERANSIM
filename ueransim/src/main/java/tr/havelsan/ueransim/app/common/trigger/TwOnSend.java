/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.trigger;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public class TwOnSend extends TriggeringWrapper {
    public final Object msg;

    public TwOnSend(BaseSimContext ctx, Object msg) {
        super(ctx);
        this.msg = msg;
    }
}
