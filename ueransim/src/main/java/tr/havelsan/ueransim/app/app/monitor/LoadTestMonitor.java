/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.monitor;

import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupFailure;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupRequest;
import tr.havelsan.ueransim.ngap0.msg.NGAP_NGSetupResponse;
import tr.havelsan.ueransim.utils.console.BaseConsole;

import java.util.concurrent.ConcurrentHashMap;

public class LoadTestMonitor extends MonitorTask {
    private final BaseConsole console;

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Long>> counters;

    public LoadTestMonitor(BaseConsole console) {
        this.console = console;
        this.counters = new ConcurrentHashMap<>();
    }

    private static String getIntervalDisplay(String id) {
        switch (id) {
            case "ng-setup": return "NGSetup";
            case "registration": return "Registration";
            case "phase1": return "Phase 1 (Registration-Authentication)";
            case "phase2": return "Phase 2 (Authentication-SecurityModeControl)";
            case "phase3": return "Phase 3 (SecurityModeControl-RegistrationAccept)";
            case "security-mode-control": return "Security Mode Control";
            case "de-registration": return "De-Registration";
            case "authentication": return "Authentication";
        }
        return id;
    }

    @Override
    public void onConnected(BaseSimContext ctx, EConnType connType) {

    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {

    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupRequest) {
            intervalStarted(ctx, "ng-setup");
        } else if (message instanceof RegistrationRequest) {
            intervalStarted(ctx, "registration");
            intervalStarted(ctx, "phase1");
        } else if (message instanceof AuthenticationResponse) {
            intervalStarted(ctx, "phase2");
        } else if (message instanceof SecurityModeComplete) {
            intervalStarted(ctx, "phase3");
            intervalEnded(ctx, "security-mode-control", true);
        } else if (message instanceof DeRegistrationRequestUeOriginating) {
            intervalStarted(ctx, "de-registration");
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupResponse) {
            intervalEnded(ctx, "ng-setup", true);
        } else if (message instanceof NGAP_NGSetupFailure) {
            intervalEnded(ctx, "ng-setup", false);
        } else if (message instanceof RegistrationReject) {
            intervalEnded(ctx, "registration", false);
        } else if (message instanceof RegistrationAccept) {
            intervalEnded(ctx, "phase3", true);
            intervalEnded(ctx, "registration", true);
        } else if (message instanceof AuthenticationRequest) {
            intervalStarted(ctx, "authentication");
            intervalEnded(ctx, "phase1", true);
        } else if (message instanceof SecurityModeCommand) {
            intervalStarted(ctx, "security-mode-control");
            intervalEnded(ctx, "phase2", true);
            intervalEnded(ctx, "authentication", true);
        } else if (message instanceof DeRegistrationAcceptUeOriginating) {
            intervalEnded(ctx, "de-registration", true);
        }
    }

    private void intervalStarted(BaseSimContext ctx, String id) {
        var map = counters.computeIfAbsent(ctx.nodeName, k -> new ConcurrentHashMap<>());
        map.put(id, System.currentTimeMillis());
    }

    private void intervalEnded(BaseSimContext ctx, String id, boolean isSuccess) {
        var map = counters.computeIfAbsent(ctx.nodeName, k -> new ConcurrentHashMap<>());
        var time = map.get(id);
        if (time == null) {
            return;
        }
        var delta = System.currentTimeMillis() - time;

        String icon = isSuccess ? "\u2714" : "\u2718";
        String intervalDisplay = getIntervalDisplay(id);

        console.println(null, "%s %s [%s] [%d ms]", icon, intervalDisplay, ctx.nodeName, delta);
    }
}