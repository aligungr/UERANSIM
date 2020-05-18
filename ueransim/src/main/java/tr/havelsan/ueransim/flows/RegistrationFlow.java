package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.UeAuthentication;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.RegistrationInput;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsRegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.ies.IENssai;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class RegistrationFlow extends BaseFlow {

    private final RegistrationInput input;

    public RegistrationFlow(SimulationContext simContext, RegistrationInput input) {
        super(simContext);
        this.input = input;
    }

    @Override
    public State main(IncomingMessage message) {
        var registrationRequest = new RegistrationRequest();
        registrationRequest.registrationType = new IE5gsRegistrationType(
                IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING,
                IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);
        registrationRequest.nasKeySetIdentifier = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, input.ngKSI);
        registrationRequest.requestedNSSAI = new IENssai(input.requestNssai);
        registrationRequest.mobileIdentity = ctx.ueData.imsi;

        send(new SendingMessage(new NgapBuilder(NgapProcedure.InitialUEMessage, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addProtocolIE(new RRCEstablishmentCause(input.rrcEstablishmentCause), NgapCriticality.IGNORE), registrationRequest));

        return this::waitAmfMessages;
    }

    private State waitAmfMessages(IncomingMessage message) {
        var initialContextSetupRequest = message.getNgapMessage(InitialContextSetupRequest.class);
        if (initialContextSetupRequest != null) {
            return handleInitialContextSetup();
        }

        var nasMessage = message.getNasMessage(NasMessage.class);
        if (nasMessage != null) {
            return handleNasMessage(nasMessage);
        }

        logUnhandledMessage(message);
        return this::waitAmfMessages;
    }

    private State handleNasMessage(NasMessage message) {
        if (message instanceof AuthenticationRequest) {
            return handleAuthenticationRequest((AuthenticationRequest) message);
        } else if (message instanceof AuthenticationResult) {
            Console.println(Color.BLUE, "Authentication result received");
            return this::waitAmfMessages;
        } else if (message instanceof RegistrationReject) {
            return flowFailed("RegistrationReject result received.");
        } else if (message instanceof IdentityRequest) {
            return handleIdentityRequest((IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            return handleRegistrationAccept((RegistrationAccept) message);
        } else {
            logUnhandledMessage(message);
            return this::waitAmfMessages;
        }
    }

    private State handleInitialContextSetup() {
        send(new SendingMessage(new NgapBuilder(NgapProcedure.InitialContextSetupResponse, NgapCriticality.REJECT)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.IGNORE), null));

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT), new RegistrationComplete()));

        return flowComplete();
    }

    private State handleRegistrationAccept(RegistrationAccept message) {
        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT), new RegistrationComplete()));

        return flowComplete();
    }

    private State handleAuthenticationRequest(AuthenticationRequest message) {
        var response = UeAuthentication.handleAuthenticationRequest(ctx, input, message);
        if (response != null) {
            send(response);
        }
        return this::waitAmfMessages;
    }

    private State handleIdentityRequest(IdentityRequest message) {
        IdentityResponse response = new IdentityResponse();

        if (message.identityType.value.equals(EIdentityType.IMEI)) {
            response.mobileIdentity = ctx.ueData.imei;
        } else if (message.identityType.value.equals(EIdentityType.SUCI)) {
            response.mobileIdentity = ctx.ueData.imsi;
        } else {
            Console.println(Color.YELLOW, "Identity request for %s is not implemented yet",
                    message.identityType.value.name());
            return this::waitAmfMessages;
        }

        send(new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT), response));

        return this::waitAmfMessages;
    }
}
