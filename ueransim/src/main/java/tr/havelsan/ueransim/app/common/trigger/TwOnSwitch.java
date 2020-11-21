/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.trigger;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public class TwOnSwitch extends TriggeringWrapper {
    public final Object state;

    public TwOnSwitch(BaseSimContext ctx, Object state) {
        super(ctx);
        this.state = state;
    }
}
