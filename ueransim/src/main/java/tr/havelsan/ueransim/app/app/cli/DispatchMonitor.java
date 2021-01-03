/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
