/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.app.common.enums.ERrcState;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nts.nts.NtsTask;

public class UeRrcContext {
    public final UeSimContext ueCtx;
    public NtsTask mrTask;
    public NtsTask nasTask;

    public ERrcState state;

    public UeRrcContext(UeSimContext ueCtx) {
        this.ueCtx = ueCtx;
        this.state = ERrcState.RRC_IDLE;
    }
}
