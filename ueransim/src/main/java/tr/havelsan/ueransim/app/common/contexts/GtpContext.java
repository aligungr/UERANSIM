/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.dynamics.PduSessionTree;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.itms.nts.NtsTask;

import java.net.DatagramSocket;

public class GtpContext {

    public final GnbSimContext gnbCtx;
    public final PduSessionTree pduSessions;
    public DatagramSocket socket;
    public NtsTask mrTask;

    public GtpContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.pduSessions = new PduSessionTree();
    }
}
