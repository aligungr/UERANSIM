/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
