/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.cli;

import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;

public class DispatchMonitor extends MonitorTask {

    @Override
    protected void onConnected(BaseSimContext ctx, EConnType connType) {

    }

    @Override
    protected void onSend(BaseSimContext ctx, Object message) {

    }

    @Override
    protected void onReceive(BaseSimContext ctx, Object message) {

    }

    @Override
    protected void onSwitched(BaseSimContext ctx, Enum<?> state) {

    }
}
