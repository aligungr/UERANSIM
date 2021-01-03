/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.utils.console.Logger;

public class UeSimContext extends BaseSimContext {
    public Logger logger;

    public UeConfig ueConfig;

    public UeSimContext(UeRanSim sim, String nodeName) {
        super(sim, nodeName);
    }
}
