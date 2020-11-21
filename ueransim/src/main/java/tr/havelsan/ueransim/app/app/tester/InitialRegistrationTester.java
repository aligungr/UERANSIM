/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_InitialRegistration;
import tr.havelsan.ueransim.itms.ItmsId;
import tr.havelsan.ueransim.nas.impl.enums.EFollowOnRequest;

class InitialRegistrationTester extends UeTester {

    public InitialRegistrationTester(UeSimContext ctx, ProcTestConfig config) {
        super(ctx, config);
    }

    @Override
    public void onStart() {
        ctx.itms.sendMessage(ItmsId.UE_TASK_APP,
                new IwUeTestCommand(new TestCmd_InitialRegistration(EFollowOnRequest.FOR_PENDING)));
    }

    @Override
    public void onConnected(BaseSimContext ctx, ConnType connType) {

    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {

    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {

    }

    @Override
    public void onSwitched(BaseSimContext ctx) {

    }
}
