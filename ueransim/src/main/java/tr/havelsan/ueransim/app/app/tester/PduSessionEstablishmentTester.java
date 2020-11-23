/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.app.monitor.MonitorTask;
import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.itms.IwUeTestCommand;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_PduSessionEstablishment;
import tr.havelsan.ueransim.app.common.testcmd.TestCmd_Ping;
import tr.havelsan.ueransim.itms.ItmsId;

class PduSessionEstablishmentTester extends UeTester {

    private UeTester otherTester;

    public PduSessionEstablishmentTester(MonitorTask task, UeSimContext ctx, ProcTestConfig config) {
        super(task, ctx, config);
    }

    @Override
    public void onStart() {
        otherTester = new InitialRegistrationTester(task, ctx, config) {
            @Override
            public void onComplete() {
                otherTester = null;
                ctx.nts.findTask(ItmsId.UE_TASK_APP).push(new IwUeTestCommand(new TestCmd_PduSessionEstablishment()));
            }
        };
        otherTester.onStart();
    }

    @Override
    public void onComplete() {
        if (otherTester != null) {
            otherTester.onComplete();
            return;
        }
    }

    @Override
    public void onConnected(BaseSimContext ctx, EConnType connType) {
        if (otherTester != null) {
            otherTester.onConnected(ctx, connType);
            return;
        }

        if (connType == EConnType.ANY_IPv4) {
            ctx.nts.findTask(ItmsId.UE_TASK_APP).push(new IwUeTestCommand(new TestCmd_Ping(config.pingAddress, config.pingCount, config.pingTimeoutSec)));
        }
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        if (otherTester != null) {
            otherTester.onSend(ctx, message);
            return;
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (otherTester != null) {
            otherTester.onReceive(ctx, message);
            return;
        }
    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {
        if (otherTester != null) {
            otherTester.onSwitched(ctx, state);
            return;
        }
    }
}
