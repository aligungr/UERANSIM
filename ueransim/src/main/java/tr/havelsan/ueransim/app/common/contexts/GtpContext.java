/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.PduSessionResource;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;

import java.util.ArrayList;
import java.util.List;

public class GtpContext {

    public final GnbSimContext gnbCtx;

    // TODO: Make O(1)
    public final List<PduSessionResource> pduSessions;

    public GtpContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.pduSessions = new ArrayList<>();
    }
}
