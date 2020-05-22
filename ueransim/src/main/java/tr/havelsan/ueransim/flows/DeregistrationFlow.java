package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.FlowLogging;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.configs.DeregistrationConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeOriginating;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;

public class DeregistrationFlow extends BaseFlow {

    private final DeregistrationConfig config;

    public DeregistrationFlow(SimulationContext simContext, DeregistrationConfig config) {
        super(simContext);
        this.config = config;
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        var request = new DeRegistrationRequestUeOriginating();
        request.deRegistrationType = config.deregistrationType;
        request.ngKSI = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, config.ngKSI);
        request.mobileIdentity = config.guti;

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), request));

        return this::waitDeregistrationAccept;
    }

    private State waitDeregistrationAccept(IncomingMessage message) {
        var deRegistrationAcceptUeOriginating = message.getNasMessage(DeRegistrationAcceptUeOriginating.class);
        if (deRegistrationAcceptUeOriginating == null) {
            FlowLogging.logUnhandledMessage(message, DeRegistrationRequestUeOriginating.class);
            return this::waitDeregistrationAccept;
        }
        return this::waitUeContextReleaseCommand;
    }

    private State waitUeContextReleaseCommand(IncomingMessage message) {
        var command = message.getNgapMessage(UEContextReleaseCommand.class);
        if (command == null) {
            FlowLogging.logUnhandledMessage(message, UEContextReleaseCommand.class);
            return this::waitDeregistrationAccept;
        }

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UEContextReleaseComplete, NgapCriticality.REJECT), null));

        return flowComplete();
    }
}
