package tr.havelsan.ueransim.api.ue;

import tr.havelsan.ueransim.api.ue.mm.*;
import tr.havelsan.ueransim.api.ue.sm.UePduSessionEstablishment;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.utils.FlowLogging;

public class UserEquipment {

    public static void handleNasMessage(UeSimContext ctx, NasMessage message) {
        if (message instanceof AuthenticationRequest) {
            UeAuthentication.handleAuthenticationRequest(ctx, (AuthenticationRequest) message);
        } else if (message instanceof AuthenticationResult) {
            UeAuthentication.handleAuthenticationResult(ctx, (AuthenticationResult) message);
        } else if (message instanceof AuthenticationResponse) {
            UeAuthentication.handleAuthenticationResponse(ctx, (AuthenticationResponse) message);
        } else if (message instanceof AuthenticationReject) {
            UeAuthentication.handleAuthenticationReject(ctx, (AuthenticationReject) message);
        } else if (message instanceof RegistrationReject) {
            UeRegistration.handleRegistrationReject(ctx, (RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            UeIdentity.handleIdentityRequest(ctx, (IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            UeRegistration.handleRegistrationAccept(ctx, (RegistrationAccept) message);
        } else if (message instanceof ServiceAccept) {
            UeService.handleServiceAccept(ctx, (ServiceAccept) message);
        } else if (message instanceof ServiceReject) {
            UeService.handleServiceReject(ctx, (ServiceReject) message);
        } else if (message instanceof SecurityModeCommand) {
            UeSecurity.handleSecurityModeCommand(ctx, (SecurityModeCommand) message);
        } else if (message instanceof PduSessionEstablishmentAccept) {
            UePduSessionEstablishment.handleEstablishmentAccept(ctx, (PduSessionEstablishmentAccept) message);
        } else if (message instanceof PduSessionEstablishmentReject) {
            UePduSessionEstablishment.handleEstablishmentReject(ctx, (PduSessionEstablishmentReject) message);
        } else {
            FlowLogging.logUnhandledMessage(message);
        }
    }
}
