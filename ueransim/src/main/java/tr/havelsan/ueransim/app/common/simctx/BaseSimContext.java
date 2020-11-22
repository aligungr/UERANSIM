/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.itms.nts.NtsBase;

import java.util.UUID;

public class BaseSimContext {
    public final UeRanSim sim;
    public final UUID ctxId;
    public final NtsBase nts;
    public final String nodeName;

    public BaseSimContext(UeRanSim sim, String nodeName) {
        this.sim = sim;
        this.ctxId = UUID.randomUUID();
        this.nts = new NtsBase();
        this.nodeName = nodeName;
    }
}
