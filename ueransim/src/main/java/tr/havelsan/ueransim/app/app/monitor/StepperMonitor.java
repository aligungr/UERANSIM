/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.monitor;

import tr.havelsan.ueransim.app.common.enums.EConnType;
import tr.havelsan.ueransim.app.common.simctx.BaseSimContext;
import tr.havelsan.ueransim.app.common.sw.SwStep;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap0.core.NGAP_BaseMessage;
import tr.havelsan.ueransim.ngap0.core.NGAP_Value;
import tr.havelsan.ueransim.utils.Json;
import tr.havelsan.ueransim.utils.Severity;

import java.util.function.Consumer;

public class StepperMonitor extends MonitorTask {

    private final Consumer<SwStep> stepConsumer;

    public StepperMonitor(Consumer<SwStep> stepConsumer) {
        this.stepConsumer = stepConsumer;
    }

    private static Severity messageSeverity(Object msg) {
        if (msg instanceof NGAP_Value) {
            if (msg instanceof NGAP_BaseMessage) {
                var ngap = (NGAP_BaseMessage) msg;
                switch (ngap.getPduType()) {
                    case 1:
                        return Severity.SUCC;
                    case 2:
                        return Severity.ERRO;
                    default:
                        return Severity.INFO;
                }
            } else {
                return Severity.INFO;
            }
        } else if (msg instanceof NasMessage) {
            return nasSeverity((NasMessage) msg);
        } else {
            return Severity.INFO;
        }
    }

    private static Severity nasSeverity(NasMessage msg) {
        if (msg instanceof AuthenticationFailure || msg instanceof AuthenticationReject || msg instanceof FiveGMmStatus
                || msg instanceof FiveGSmStatus || msg instanceof PduSessionEstablishmentReject ||
                msg instanceof PduSessionModificationReject || msg instanceof PduSessionReleaseReject ||
                msg instanceof RegistrationReject || msg instanceof SecurityModeReject) {
            return Severity.ERRO;
        }

        if (msg instanceof ConfigurationUpdateComplete || msg instanceof PduSessionAuthenticationComplete ||
                msg instanceof PduSessionEstablishmentAccept || msg instanceof PduSessionModificationComplete
                || msg instanceof PduSessionReleaseComplete || msg instanceof RegistrationAccept ||
                msg instanceof RegistrationComplete || msg instanceof SecurityModeComplete
                || msg instanceof ServiceAccept) {
            return Severity.SUCC;
        }

        return Severity.INFO;
    }

    private void onMessage(BaseSimContext ctx, Object message) {
        if (message == null) {
            return;
        }

        var loggerName = ctx.nodeName;
        var severity = messageSeverity(message);
        var messageName = message.getClass().getSimpleName();
        if (messageName.startsWith("NGAP_"))
            messageName = messageName.substring("NGAP_".length());

        var messageBody = Json.toJson(message);

        stepConsumer.accept(new SwStep(loggerName, severity, messageName, messageBody));
    }

    @Override
    public void onConnected(BaseSimContext ctx, EConnType connectionType) {

    }

    @Override
    public void onSend(BaseSimContext ctx, Object message) {
        onMessage(ctx, message);
    }

    @Override
    public void onReceive(BaseSimContext ctx, Object message) {
        onMessage(ctx, message);
    }

    @Override
    public void onSwitched(BaseSimContext ctx, Enum<?> state) {

    }
}
