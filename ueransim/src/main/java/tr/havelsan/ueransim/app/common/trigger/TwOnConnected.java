/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.trigger;

import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public class TwOnConnected extends TriggeringWrapper {
    public final EConnType connType;

    public TwOnConnected(BaseSimContext ctx, EConnType connType) {
        super(ctx);
        this.connType = connType;
    }
}
