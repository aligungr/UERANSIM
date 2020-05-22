package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.configs.RegistrationConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsRegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.ies.IENssai;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationAccept;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationComplete;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationReject;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;
import tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;

public class UeRegistration {

    public static void sendInitialRegistration(SimulationContext ctx, RegistrationConfig config) {
        var ngKsi = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, IENasKeySetIdentifier.NOT_AVAILABLE_OR_RESERVED);
        if (ctx.nasSecurityContext != null && ctx.nasSecurityContext.ngKsi != null) {
            ngKsi = ctx.nasSecurityContext.ngKsi;
        }

        var registrationRequest = new RegistrationRequest();
        registrationRequest.registrationType = new IE5gsRegistrationType(
                IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING,
                IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);
        registrationRequest.nasKeySetIdentifier = ngKsi;
        registrationRequest.requestedNSSAI = new IENssai(config.requestNssai);

        if (config.use5gGuti) {
            registrationRequest.mobileIdentity = config.gutiMobileIdentity;
        } else {
            registrationRequest.mobileIdentity = UeIdentity.generateSuciFromSupi(ctx.ueData.supi);
        }

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.InitialUEMessage, NgapCriticality.IGNORE)
                .addProtocolIE(new RRCEstablishmentCause(config.rrcEstablishmentCause), NgapCriticality.IGNORE), registrationRequest));
    }

    public static void handleRegistrationAccept(SimulationContext ctx, RegistrationAccept message) {
        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE),
                new RegistrationComplete()));
    }

    public static void handleRegistrationReject(SimulationContext ctx, RegistrationReject message) {
        Console.println(Color.RED_BOLD, "RegistrationReject result received.");
    }
}
