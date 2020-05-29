package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.configs.RegistrationConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.impl.enums.ERegistrationType;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.*;
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
import tr.havelsan.ueransim.utils.bits.Bit;

public class UeRegistration {

    public static void sendRegistration(SimulationContext ctx, RegistrationConfig config, ERegistrationType registrationType) {
        var ngKsi = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, IENasKeySetIdentifier.NOT_AVAILABLE_OR_RESERVED);
        if (ctx.nasSecurityContext != null && ctx.nasSecurityContext.ngKsi != null) {
            ngKsi = ctx.nasSecurityContext.ngKsi;
        }

        var registrationRequest = new RegistrationRequest();
        registrationRequest.registrationType = new IE5gsRegistrationType(
                IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING,
                registrationType);
        registrationRequest.nasKeySetIdentifier = ngKsi;
        registrationRequest.requestedNSSAI = new IENssai(config.requestNssai);
        registrationRequest.ueSecurityCapability = createSecurityCapabilityIe();

        if (ctx.guti != null) {
            registrationRequest.mobileIdentity = ctx.guti;
        } else {
            registrationRequest.mobileIdentity = UeIdentity.generateSuciFromSupi(ctx.ueData.supi);
        }

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.InitialUEMessage, NgapCriticality.IGNORE)
                .addProtocolIE(new RRCEstablishmentCause(config.rrcEstablishmentCause), NgapCriticality.IGNORE), registrationRequest));
    }

    private static IEUeSecurityCapability createSecurityCapabilityIe() {
        var ie = new IEUeSecurityCapability();
        ie.supported_5G_EA0 = new Bit(1);
        ie.supported_128_5G_EA1 = new Bit(1);
        ie.supported_128_5G_EA2 = new Bit(1);
        ie.supported_128_5G_EA3 = new Bit(1);
        ie.supported_5G_EA4 = new Bit(0);
        ie.supported_5G_EA5 = new Bit(0);
        ie.supported_5G_EA6 = new Bit(0);
        ie.supported_5G_EA7 = new Bit(0);
        ie.supported_5G_IA0 = new Bit(1);
        ie.supported_128_5G_IA1 = new Bit(1);
        ie.supported_128_5G_IA2 = new Bit(1);
        ie.supported_128_5G_IA3 = new Bit(1);
        ie.supported_5G_IA4 = new Bit(0);
        ie.supported_5G_IA5 = new Bit(0);
        ie.supported_5G_IA6 = new Bit(0);
        ie.supported_5G_IA7 = new Bit(0);
        ie.supported_EEA0 = new Bit(1);
        ie.supported_128_EEA1 = new Bit(1);
        ie.supported_128_EEA2 = new Bit(1);
        ie.supported_128_EEA3 = new Bit(1);
        ie.supported_EEA4 = new Bit(0);
        ie.supported_EEA5 = new Bit(0);
        ie.supported_EEA6 = new Bit(0);
        ie.supported_EEA7 = new Bit(0);
        ie.supported_EIA0 = new Bit(1);
        ie.supported_128_EIA1 = new Bit(1);
        ie.supported_128_EIA2 = new Bit(1);
        ie.supported_128_EIA3 = new Bit(1);
        ie.supported_EIA4 = new Bit(0);
        ie.supported_EIA5 = new Bit(0);
        ie.supported_EIA6 = new Bit(0);
        ie.supported_EIA7 = new Bit(0);
        return ie;
    }

    public static void handleRegistrationAccept(SimulationContext ctx, RegistrationAccept message) {
        if (message.mobileIdentity instanceof IE5gGutiMobileIdentity) {
            ctx.guti = (IE5gGutiMobileIdentity) message.mobileIdentity;
        }

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE),
                new RegistrationComplete()));
    }

    public static void handleRegistrationReject(SimulationContext ctx, RegistrationReject message) {
        Console.println(Color.RED_BOLD, "RegistrationReject result received.");
    }
}
