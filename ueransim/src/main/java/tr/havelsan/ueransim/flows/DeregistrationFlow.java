package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.DeregistrationInput;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationAcceptUeOriginating;
import tr.havelsan.ueransim.nas.impl.messages.DeRegistrationRequestUeOriginating;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.UEContextReleaseCommand;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class DeregistrationFlow extends BaseFlow {

    private final DeregistrationInput input;

    public DeregistrationFlow(SimulationContext simContext, DeregistrationInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(IncomingMessage message) throws Exception {
        var request = new DeRegistrationRequestUeOriginating();
        request.deRegistrationType = input.deregistrationType;
        request.ngKSI = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, input.ngKSI);
        request.mobileIdentity = input.guti;

        send(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE), request);

        return this::waitDeregistrationAccept;
    }

    private State waitDeregistrationAccept(IncomingMessage message) {
        var deRegistrationAcceptUeOriginating = message.getNasMessage(DeRegistrationAcceptUeOriginating.class);
        if (deRegistrationAcceptUeOriginating == null) {
            Console.println(Color.YELLOW, "bad message, DeRegistrationAcceptUeOriginating is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        return this::waitUeContextReleaseCommand;
    }

    private State waitUeContextReleaseCommand(IncomingMessage message) {
        var command = message.getNgapMessage(UEContextReleaseCommand.class);
        if (command == null) {
            Console.println(Color.YELLOW, "bad message, UEContextReleaseCommand is expected. message ignored");
            return this::waitDeregistrationAccept;
        }

        send(new NgapBuilder(NgapProcedure.UEContextReleaseComplete, NgapCriticality.REJECT)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.IGNORE), null);

        return flowComplete();
    }
}
