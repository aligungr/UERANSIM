/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.simctx;

import tr.havelsan.ueransim.app.app.UeRanSim;
import tr.havelsan.ueransim.app.common.UeData;
import tr.havelsan.ueransim.app.common.UeTimers;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.contexts.MmContext;
import tr.havelsan.ueransim.app.common.contexts.SmContext;
import tr.havelsan.ueransim.app.ue.nas.NasSecurityContext;
import tr.havelsan.ueransim.utils.console.Logger;

import java.util.UUID;

public class UeSimContext extends BaseSimContext {
    public Logger logger;

    public UeData ueData;
    public UeConfig ueConfig;
    public UeTimers ueTimers;

    public UUID connectedGnb;

    public MmContext mmCtx;
    public SmContext smCtx;
    public NasSecurityContext currentNsCtx;
    public NasSecurityContext nonCurrentNsCtx;

    public UeSimContext(UeRanSim sim, String nodeName) {
        super(sim, nodeName);
        this.ueTimers = new UeTimers(this);
        this.mmCtx = new MmContext();
        this.smCtx = new SmContext();
        this.ueData = new UeData();
    }
}
