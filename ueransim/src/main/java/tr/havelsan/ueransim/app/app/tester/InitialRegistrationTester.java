/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.enums.ERmState;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_InitialRegistration;
import tr.havelsan.ueransim.itms.ItmsId;

class InitialRegistrationTester extends UeTester {

    public InitialRegistrationTester(MonitorTask task, UeSimContext ctx, ProcTestConfig config) {
        super(task, ctx, config);
    }

    @Override
    public void onStart() {
        ctx.nts.findTask(ItmsId.UE_TASK_APP).push(new IwUeTestCommand(new TestCmd_InitialRegistration(config.forPending)));
    }

    @Override
    public void onConnected(BaseSimContext ctx, EConnType connType) {
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {

    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {

    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {
        if (state == ERmState.RM_REGISTERED) {
            onComplete();
        }
    }

    @Override
    public void onComplete() {

    }
}
