package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.configs.RegistrationConfig;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.impl.enums.EFollowOnRequest;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
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
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit;

public class UeRegistration {

    public static void sendRegistration(SimulationContext ctx, RegistrationConfig config, ERegistrationType registrationType) {
        var ngKsi = new IENasKeySetIdentifier(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, IENasKeySetIdentifier.NOT_AVAILABLE_OR_RESERVED);
        if (ctx.currentNsc != null && ctx.currentNsc.ngKsi != null) {
            ngKsi = ctx.currentNsc.ngKsi;
        }

        var registrationRequest = new RegistrationRequest();
        registrationRequest.registrationType = new IE5gsRegistrationType(
                registrationType.equals(ERegistrationType.EMERGENCY_REGISTRATION) ? EFollowOnRequest.FOR_PENDING :
                        EFollowOnRequest.NO_FOR_PENDING,
                registrationType);
        registrationRequest.nasKeySetIdentifier = ngKsi;
        registrationRequest.requestedNSSAI = new IENssai(ctx.ueConfig.requestedNssai);
        registrationRequest.ueSecurityCapability = UeSecurity.createSecurityCapabilityIe();
        registrationRequest.updateType = new IE5gsUpdateType(
                ctx.ueConfig.smsOverNasSupported ? IE5gsUpdateType.ESmsRequested.SUPPORTED : IE5gsUpdateType.ESmsRequested.NOT_SUPPORTED,
                IE5gsUpdateType.ENgRanRadioCapabilityUpdate.NOT_NEEDED);

        if (ctx.ueData.storedGuti != null) {
            registrationRequest.mobileIdentity = ctx.ueData.storedGuti;
        } else {
            var suci = UeIdentity.getOrGenerateSuci(ctx);
            if (suci != null) {
                registrationRequest.mobileIdentity = suci;

                if (!ctx.ueTimers.t3519.isRunning()) {
                    ctx.ueTimers.t3519.start();
                }

            } else {
                registrationRequest.mobileIdentity = new IEImeiMobileIdentity(ctx.ueData.imei);
            }
        }

        if (ctx.lastVisitedRegisteredTai != null) {
            registrationRequest.lastVisitedRegisteredTai = ctx.lastVisitedRegisteredTai;
        }

        ctx.registrationRequest = registrationRequest;

        ctx.ueTimers.t3510.start();
        ctx.ueTimers.t3502.stop();
        ctx.ueTimers.t3511.stop();

        Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.InitialUEMessage, NgapCriticality.IGNORE)
                .addProtocolIE(new RRCEstablishmentCause(config.rrcEstablishmentCause), NgapCriticality.IGNORE), registrationRequest));
    }

    public static void handleRegistrationAccept(SimulationContext ctx, RegistrationAccept message) {
        boolean sendCompleteMes = false;

        ctx.taiList = message.taiList;

        if (message.t3512Value != null && message.t3512Value.hasValue()) {
            ctx.ueTimers.t3512.start(message.t3512Value);
        }

        if (message.mobileIdentity instanceof IE5gGutiMobileIdentity) {
            ctx.ueData.storedGuti = (IE5gGutiMobileIdentity) message.mobileIdentity;
            ctx.ueTimers.t3519.stop();

            sendCompleteMes = true;
        }

        if (sendCompleteMes) {
            Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE),
                    new RegistrationComplete()));
        }
    }

    public static void handleRegistrationReject(SimulationContext ctx, RegistrationReject message) {
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                UeAuthentication.handleEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "network sent EAP with type of %s in RegistrationReject, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }

        var cause = message.mmCause.value;

        var regType = ctx.registrationRequest.registrationType.registrationType;

        if (regType.equals(ERegistrationType.INITIAL_REGISTRATION)) {
            if (cause.equals(EMmCause.ILLEGAL_UE) || cause.equals(EMmCause.ILLEGAL_ME)) {
                ctx.ueData.storedGuti = null;
                ctx.lastVisitedRegisteredTai = null;
                ctx.taiList = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;
            } else if (cause.equals(EMmCause.FIVEG_SERVICES_NOT_ALLOWED)) {
                ctx.ueData.storedGuti = null;
                ctx.lastVisitedRegisteredTai = null;
                ctx.taiList = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;
            } else if (cause.equals(EMmCause.PLMN_NOT_ALLOWED)) {
                ctx.ueData.storedGuti = null;
                ctx.lastVisitedRegisteredTai = null;
                ctx.taiList = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;
            } else if (cause.equals(EMmCause.TA_NOT_ALLOWED)) {
                ctx.ueData.storedGuti = null;
                ctx.lastVisitedRegisteredTai = null;
                ctx.taiList = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;
            } else if (cause.equals(EMmCause.ROAMING_NOT_ALLOWED_IN_TA)) {
                ctx.ueData.storedGuti = null;
                ctx.lastVisitedRegisteredTai = null;
                ctx.taiList = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;
            } else if (cause.equals(EMmCause.NO_SUITIBLE_CELLS_IN_TA)) {
                ctx.ueData.storedGuti = null;
                ctx.lastVisitedRegisteredTai = null;
                ctx.taiList = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;
            } else if (cause.equals(EMmCause.CONGESTION)) {
                if (message.t3346value != null && message.t3346value.hasValue()) {
                    ctx.ueTimers.t3346.stop();

                    if (message.securityHeaderType.isIntegrityProtected()) {
                        ctx.ueTimers.t3346.start(message.t3346value);
                    } else {
                        // todo
                    }
                } else {
                    // todo
                }
            } else if (cause.equals(EMmCause.N1_MODE_NOT_ALLOWED)) {
                // todo
            } else if (cause.equals(EMmCause.NON_3GPP_ACCESS_TO_CN_NOT_ALLOWED)) {
                // todo
            } else if (cause.equals(EMmCause.SERVING_NETWORK_NOT_AUTHORIZED)) {
                // todo
            }
        } else if (regType.equals(ERegistrationType.EMERGENCY_REGISTRATION)) {
            if (cause.equals(EMmCause.PEI_NOT_ACCEPTED)) {
                // todo
            } else {
                // todo: abnormal case
            }
        } else {
            // todo
        }
    }
}
