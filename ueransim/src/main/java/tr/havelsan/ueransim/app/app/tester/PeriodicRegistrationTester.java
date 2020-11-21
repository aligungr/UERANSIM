/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.tester;

import tr.havelsan.ueransim.app.common.configs.ProcTestConfig;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;

class PeriodicRegistrationTester extends UeTester {

    public PeriodicRegistrationTester(UeSimContext ctx, ProcTestConfig config) {
        super(ctx, config);
    }

    @Override
    public void onStart() {
        // TODO
    }

    @Override
    public void onComplete() {
        // TODO
    }

    @Override
    public void onConnected(BaseSimContext ctx, ConnType connType) {
        // TODO
    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        // TODO
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        // TODO
    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {
        // TODO
    }
}
