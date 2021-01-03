/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nts.nts.NtsTask;
import tr.havelsan.ueransim.utils.Timing;

import java.util.UUID;

public class UeMrContext {
    public final UeSimContext ueCtx;
    public final Timing noPlmnWarning;
    public NtsTask rrcTask;
    public NtsTask appTask;
    public UUID connectedGnb;
    public String connectedGnbName;

    public UeMrContext(UeSimContext ueCtx) {
        this.ueCtx = ueCtx;
        this.noPlmnWarning = new Timing();
    }
}
