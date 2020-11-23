/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;

abstract class UeTester {

    protected final MonitorTask task;
    protected final UeSimContext ctx;
    protected final ProcTestConfig config;

    public UeTester(MonitorTask task, UeSimContext ctx, ProcTestConfig config) {
        this.task = task;
        this.ctx = ctx;
        this.config = config;
    }

    public abstract void onStart();

    public abstract void onComplete();

    public abstract void onConnected(BaseSimContext ctx, EConnType connType);

    public abstract void onSend(BaseSimContext ctx, Object message);

    public abstract void onReceive(BaseSimContext ctx, Object message);

    public abstract void onSwitched(BaseSimContext ctx, Enum<?> state);
}
