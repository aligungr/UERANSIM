/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.nts.nts.NtsTask;

import java.util.HashMap;
import java.util.UUID;

public class NgapGnbContext {
    public final GnbSimContext gnbCtx;

    public NtsTask rrcTask;
    public NtsTask gtpTask;
    public NtsTask sctpTask;
    public NtsTask appTask;

    public HashMap<UUID, NgapAmfContext> amfContexts;
    public HashMap<UUID, NgapUeContext> ueContexts;
    public long ueNgapIdCounter;

    public int waitingSctpClients;

    public int downTeidCounter;

    public NgapGnbContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.amfContexts = new HashMap<>();
        this.ueContexts = new HashMap<>();
    }
}
