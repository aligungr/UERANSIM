/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.utils.console.Logger;

public class AirSimContext extends BaseSimContext {
    public Logger logger;

    public AirSimContext(UeRanSim sim, String nodeName) {
        super(sim, nodeName);
    }
}
