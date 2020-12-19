/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.app.gnb.gtp.PduSessionTree;
import tr.havelsan.ueransim.nts.nts.NtsTask;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GtpContext {

    public final GnbSimContext gnbCtx;
    public final PduSessionTree pduSessions;
    public final Map<UUID, GtpUeContext> ueMap;
    public DatagramSocket socket;
    public NtsTask mrTask;

    public GtpContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.pduSessions = new PduSessionTree();
        this.ueMap = new HashMap<>();
    }
}
