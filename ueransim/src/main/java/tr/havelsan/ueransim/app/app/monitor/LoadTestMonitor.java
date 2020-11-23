/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.monitor;

import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.simctx.GnbSimContext;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.console.BaseConsole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoadTestMonitor extends MonitorTask {
    private final BaseConsole console;

    private final Map<Integer, Long> ngSetupTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> registrationTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> authenticationTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> securityModeControlTimers = new ConcurrentHashMap<>();
    private final Map<String, Long> phase1Timers = new ConcurrentHashMap<>();
    private final Map<String, Long> phase2Timers = new ConcurrentHashMap<>();
    private final Map<String, Long> phase3Timers = new ConcurrentHashMap<>();
    private final Map<String, Long> deRegistrationTimers = new ConcurrentHashMap<>();

    public LoadTestMonitor(BaseConsole console) {
        this.console = console;
    }

    @Override
    public void onConnected(BaseSimContext ctx, EConnType connType) {

    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupRequest) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            ngSetupTimers.put(gnbId, System.currentTimeMillis());
        } else if (message instanceof RegistrationRequest) {
            registrationTimers.put(ctx.nodeName, System.currentTimeMillis());
            phase1Timers.put(ctx.nodeName, System.currentTimeMillis());
        } else if (message instanceof AuthenticationResponse) {
            phase2Timers.put(ctx.nodeName, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - authenticationTimers.get(ctx.nodeName);
            console.println(null, "\u2714 [Authentication (UE/RAN)] [%s] [%d ms]", ctx.nodeName, delta);
        } else if (message instanceof SecurityModeComplete) {
            phase3Timers.put(ctx.nodeName, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - securityModeControlTimers.get(ctx.nodeName);
            console.println(null, "\u2714 [Security Mode Control (UE/RAN)] [%s] [%d ms]", ctx.nodeName, delta);
        } else if (message instanceof DeRegistrationRequestUeOriginating) {
            deRegistrationTimers.put(ctx.nodeName, System.currentTimeMillis());
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupFailure) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
            console.println(null, "\u2718 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
        } else if (message instanceof NGAP_NGSetupResponse) {
            int gnbId = ((GnbSimContext) ctx).config.gnbId;
            long delta = System.currentTimeMillis() - ngSetupTimers.get(gnbId);
            console.println(null, "\u2714 [NGSetup] [gnbId: %d] [%d ms]", gnbId, delta);
        } else if (message instanceof RegistrationReject) {
            long delta = System.currentTimeMillis() - registrationTimers.get(ctx.nodeName);
            console.println(null, "\u2718 [Registration] [%s] [%d ms]", ctx.nodeName, delta);
        } else if (message instanceof RegistrationAccept) {
            long delta = System.currentTimeMillis() - registrationTimers.get(ctx.nodeName);
            console.println(null, "\u2714 [Registration] [%s] [%d ms]", ctx.nodeName, delta);

            long delta2 = System.currentTimeMillis() - phase3Timers.get(ctx.nodeName);
            console.println(null, "\u2714 [Phase 3 (Network)] [SecurityModeControl-RegistrationAccept] [%s] [%d ms]", ctx.nodeName, delta2);
        } else if (message instanceof AuthenticationRequest) {
            authenticationTimers.put(ctx.nodeName, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - phase1Timers.get(ctx.nodeName);
            console.println(null, "\u2714 [Phase 1 (Network)] [Registration-Authentication] [%s] [%d ms]", ctx.nodeName, delta);
        } else if (message instanceof SecurityModeCommand) {
            securityModeControlTimers.put(ctx.nodeName, System.currentTimeMillis());

            long delta = System.currentTimeMillis() - phase2Timers.get(ctx.nodeName);
            console.println(null, "\u2714 [Phase 2 (Network)] [Authentication-SecurityModeControl] [%s] [%d ms]", ctx.nodeName, delta);
        } else if (message instanceof DeRegistrationAcceptUeOriginating) {
            long delta = System.currentTimeMillis() - deRegistrationTimers.get(ctx.nodeName);
            console.println(null, "\u2714 [De-Registration] [%s] [%d ms]", ctx.nodeName, delta);
        }
    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {

    }
}