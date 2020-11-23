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

import java.util.concurrent.ConcurrentHashMap;

public class LoadTestMonitor extends MonitorTask {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Long>> counters;

    public LoadTestMonitor() {
        this.counters = new ConcurrentHashMap<>();
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
            intervalStarted(ctx, "ngSetup");
        } else if (message instanceof RegistrationRequest) {
            intervalStarted(ctx, "registration");
            intervalStarted(ctx, "phase1");
        } else if (message instanceof AuthenticationResponse) {
            intervalStarted(ctx, "phase2");
        } else if (message instanceof SecurityModeComplete) {
            intervalStarted(ctx, "phase3");
            intervalEnded(ctx, "securityModeControl", true);
        } else if (message instanceof DeRegistrationRequestUeOriginating) {
            intervalStarted(ctx, "deregistration");
        }
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        if (message instanceof NGAP_NGSetupResponse) {
            intervalEnded(ctx, "ngSetup", true);
        } else if (message instanceof NGAP_NGSetupFailure) {
            intervalEnded(ctx, "ngSetup", false);
        } else if (message instanceof RegistrationReject) {
            intervalEnded(ctx, "registration", false);
        } else if (message instanceof RegistrationAccept) {
            intervalEnded(ctx, "phase3", true);
            intervalEnded(ctx, "registration", true);
        } else if (message instanceof AuthenticationRequest) {
            intervalStarted(ctx, "authentication");
            intervalEnded(ctx, "phase1", true);
        } else if (message instanceof SecurityModeCommand) {
            intervalStarted(ctx, "securityModeControl");
            intervalEnded(ctx, "phase2", true);
            intervalEnded(ctx, "authentication", true);
        } else if (message instanceof DeRegistrationAcceptUeOriginating) {
            intervalEnded(ctx, "deregistration", true);
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
        onIntervalResult(id, IntervalMetadata.getIntervalDisplay(id), isSuccess, ctx.nodeName, delta);
    }

    protected void onIntervalResult(String id, String display, boolean isSuccess, String nodeName, long deltaMs) {

    }

    public static class IntervalMetadata {
        public static IntervalMetadata INSTANCE = new IntervalMetadata();

        public final IntervalInfo ngSetup;
        public final IntervalInfo registration;
        public final IntervalInfo phase1;
        public final IntervalInfo phase2;
        public final IntervalInfo phase3;
        public final IntervalInfo securityModeControl;
        public final IntervalInfo authentication;
        public final IntervalInfo deregistration;

        private IntervalMetadata() {
            this.ngSetup = new IntervalInfo("ngSetup", null, getIntervalDisplay("ngSetup"));
            this.registration = new IntervalInfo("registration", null, getIntervalDisplay("registration"));
            this.phase1 = new IntervalInfo("phase1", "registration", getIntervalDisplay("phase1"));
            this.phase2 = new IntervalInfo("phase2", "registration", getIntervalDisplay("phase2"));
            this.phase3 = new IntervalInfo("phase3", "registration", getIntervalDisplay("phase3"));
            this.securityModeControl = new IntervalInfo("securityModeControl", "registration", getIntervalDisplay("securityModeControl"));
            this.authentication = new IntervalInfo("authentication", "registration", getIntervalDisplay("authentication"));
            this.deregistration = new IntervalInfo("deregistration", null, getIntervalDisplay("deregistration"));
        }

        public static String getIntervalDisplay(String id) {
            switch (id) {
                case "ngSetup":
                    return "NG Setup";
                case "registration":
                    return "Registration";
                case "phase1":
                    return "Phase 1 (Registration-Authentication)";
                case "phase2":
                    return "Phase 2 (Authentication-SecurityModeControl)";
                case "phase3":
                    return "Phase 3 (SecurityModeControl-RegistrationAccept)";
                case "securityModeControl":
                    return "Security Mode Control";
                case "deregistration":
                    return "De-Registration";
                case "authentication":
                    return "Authentication";
                default:
                    return id;
            }
        }
    }

    public static class IntervalInfo {
        public final String id;
        public final String parent;
        public final String display;

        public IntervalInfo(String id, String parent, String display) {
            this.id = id;
            this.parent = parent;
            this.display = display;
        }
    }
}