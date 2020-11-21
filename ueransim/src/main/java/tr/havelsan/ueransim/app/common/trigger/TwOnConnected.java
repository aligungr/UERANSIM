/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.trigger;

import tr.havelsan.ueransim.app.app.listeners.INodeListener;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public class TwOnConnected extends TriggeringWrapper {
    public final INodeListener.Type type;

    public TwOnConnected(BaseSimContext ctx, INodeListener.Type type) {
        super(ctx);
        this.type = type;
    }
}
