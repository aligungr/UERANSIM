package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.IncomingMessage;
import tr.havelsan.ueransim.api.UeAuthentication;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.flowinputs.RegistrationInput;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.*;
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
        registrationRequest.mobileIdentity = input.mobileIdentity;

        send(new NgapBuilder(NgapProcedure.InitialUEMessage, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.REJECT)
                .addProtocolIE(new RRCEstablishmentCause(input.rrcEstablishmentCause), NgapCriticality.IGNORE), registrationRequest);

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
        send(new NgapBuilder(NgapProcedure.InitialContextSetupResponse, NgapCriticality.REJECT)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.IGNORE), null);

        send(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE), new RegistrationComplete());

        return flowComplete();
    }

    private State handleRegistrationAccept(RegistrationAccept message) {
        send(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE), new RegistrationComplete());

        return flowComplete();
    }

    private State handleAuthenticationRequest(AuthenticationRequest message) {
        // Handle EAP-AKA'
        if (message.eapMessage != null) {
            Console.println(Color.YELLOW, "EAP-AKA' not implemented yet");
            return this::waitAmfMessages;
        }

        // Handle 5G-AKA

        //var ngKsi = message.ngKSI;
        var autnCheck = UeAuthentication.validateAutn(ctx.ueData, message.authParamRAND.value,
                message.authParamAUTN.value);
        switch (autnCheck) {
            case OK: {
                var resStar = UeAuthentication.calculateResStar(ctx.ueData, message.authParamRAND.value);
                var response = new AuthenticationResponse(new IEAuthenticationResponseParameter(resStar), null);
                send(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                        .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                        .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE), response);
                break;
            }
            case MAC_FAILURE: {
                // todo
                Console.println(Color.YELLOW, "MAC_FAILURE case not implemented yet in AUTN validation");
                return this::waitAmfMessages;
            }
            case SYNCHRONISATION_FAILURE: {
                // todo
                Console.println(Color.YELLOW, "SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
                return this::waitAmfMessages;
            }
            default: {
                var response = new AuthenticationFailure(EMmCause.UNSPECIFIED_PROTOCOL_ERROR);
                send(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                                .addUserLocationInformationNR(input.userLocationInformationNr,
                                        NgapCriticality.IGNORE),
                        response);
                break;
            }
        }
        return this::waitAmfMessages;
    }

    private State handleIdentityRequest(IdentityRequest message) {
        IdentityResponse response = new IdentityResponse();

        if (message.identityType.value.equals(EIdentityType.IMEI)) {
            response.mobileIdentity = new IEImeiMobileIdentity(ctx.ueData.imei);
        } else if (message.identityType.value.equals(EIdentityType.SUCI)) {
            if (!(input.mobileIdentity instanceof IESuciMobileIdentity)) {
                Console.println(Color.RED, "Identity request for %s is not provided in registration.yaml",
                        message.identityType.value.name());
                return this::waitAmfMessages;
            }
            response.mobileIdentity = input.mobileIdentity;
        } else {
            Console.println(Color.YELLOW, "Identity request for %s is not implemented yet",
                    message.identityType.value.name());
            return this::waitAmfMessages;
        }

        send(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE)
                .addRanUeNgapId(input.ranUeNgapId, NgapCriticality.REJECT)
                .addUserLocationInformationNR(input.userLocationInformationNr, NgapCriticality.IGNORE), response);

        return this::waitAmfMessages;
    }
}
