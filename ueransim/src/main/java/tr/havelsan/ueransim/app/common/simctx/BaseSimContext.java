/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.itms.Itms;

import java.util.UUID;

public class BaseSimContext {
    public final UeRanSim sim;
    public final UUID ctxId;
    public final Itms itms;

    public BaseSimContext(UeRanSim sim) {
        this.sim = sim;
        this.ctxId = UUID.randomUUID();
        this.itms = new Itms();
    }
}
