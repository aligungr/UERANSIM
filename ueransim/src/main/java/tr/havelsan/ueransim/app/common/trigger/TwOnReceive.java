/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.trigger;

import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public class TwOnReceive extends TriggeringWrapper {
    public final Object msg;

    public TwOnReceive(BaseSimContext ctx, Object msg) {
        super(ctx);
        this.msg = msg;
    }
}
