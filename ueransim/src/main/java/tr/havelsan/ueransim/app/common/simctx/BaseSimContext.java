/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.nts.nts.NtsBase;

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
