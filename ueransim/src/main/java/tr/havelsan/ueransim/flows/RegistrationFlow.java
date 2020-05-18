package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.FlowLogging;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.api.UeRegistration;
import tr.havelsan.ueransim.configs.RegistrationConfig;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationComplete;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

public class RegistrationFlow extends BaseFlow {
    private final RegistrationConfig config;

    public RegistrationFlow(SimulationContext simContext, RegistrationConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) {
        UeRegistration.sendInitialRegistration(ctx, config);
        return this::generalReceiver;
    }

    private State generalReceiver(IncomingMessage message) {
        var initialContextSetupRequest = message.getNgapMessage(InitialContextSetupRequest.class);
        if (initialContextSetupRequest != null) {
            return handleInitialContextSetup();
        }

        var nasMessage = message.getNasMessage(NasMessage.class);
        if (nasMessage != null) {
            Messaging.handleNasMessage(ctx, nasMessage);
        } else {
            FlowLogging.logUnhandledMessage(message);
        }
        return this::generalReceiver;
    }

    private State handleInitialContextSetup() {
        send(new SendingMessage(new NgapBuilder(NgapProcedure.InitialContextSetupResponse, NgapCriticality.REJECT),
                null));
        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE),
                new RegistrationComplete()));
        return flowComplete();
    }
}
