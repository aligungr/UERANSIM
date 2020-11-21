/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.app.listeners.INodeListener;
import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;

abstract class UeTester implements INodeListener {

    protected final UeSimContext ctx;
    protected final ProcTestConfig config;

    public UeTester(UeSimContext ctx, ProcTestConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    public abstract void onStart();
}
