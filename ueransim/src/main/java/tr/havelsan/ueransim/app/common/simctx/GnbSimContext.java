/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.configs.GnbConfig;
import tr.havelsan.ueransim.app.common.contexts.NgapGnbContext;
import tr.havelsan.ueransim.app.common.contexts.GtpUContext;
import tr.havelsan.ueransim.utils.console.Logger;

public class GnbSimContext extends BaseSimContext {
    public final GnbConfig config;
    public Logger logger;
    public NgapGnbContext ngapCtx;
    public GtpUContext gtpUCtx;

    public GnbSimContext(UeRanSim sim, String nodeName, GnbConfig config) {
        super(sim, nodeName);
        this.config = config;
        this.ngapCtx = new NgapGnbContext(this);
        this.gtpUCtx = new GtpUContext();
    }
}
