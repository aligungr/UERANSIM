/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;

import java.util.HashMap;
import java.util.UUID;

public class NgapGnbContext {
    public final GnbSimContext gnbCtx;

    public HashMap<Guami, NgapAmfContext> amfContexts;
    public HashMap<UUID, NgapUeContext> ueContexts;
    public long ueNgapIdCounter;

    public NgapGnbContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.amfContexts = new HashMap<>();
        this.ueContexts = new HashMap<>();
    }
}
