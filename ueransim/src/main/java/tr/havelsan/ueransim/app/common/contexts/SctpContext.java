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
import tr.havelsan.ueransim.sctp.SctpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SctpContext {
    public final GnbSimContext gnbCtx;
    public final Map<UUID, SctpClient> clients;

    public NtsTask ngapTask;

    public SctpContext(GnbSimContext gnbCtx) {
        this.gnbCtx = gnbCtx;
        this.clients = new HashMap<>();
    }
}
