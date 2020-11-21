/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.contexts.GnbAmfContext;
import tr.havelsan.ueransim.app.common.contexts.GnbUeContext;
import tr.havelsan.ueransim.app.common.contexts.GtpUContext;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.HashMap;
import java.util.UUID;

public class GnbSimContext extends BaseSimContext {
    public Logger logger;
    public GnbConfig config;

    public HashMap<Guami, GnbAmfContext> amfContexts;
    public HashMap<UUID, GnbUeContext> ueContexts;
    public long ueNgapIdCounter;

    public GtpUContext gtpUCtx;

    public GnbSimContext(UeRanSim sim, String nodeName) {
        super(sim, nodeName);
        this.amfContexts = new HashMap<>();
        this.ueContexts = new HashMap<>();
        this.gtpUCtx = new GtpUContext();
    }
}
