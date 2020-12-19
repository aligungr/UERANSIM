/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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
