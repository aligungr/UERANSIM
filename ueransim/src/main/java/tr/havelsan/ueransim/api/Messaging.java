package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapInternal;

public class Messaging {

    public static void send(SimulationContext ctx, SendingMessage sendingMessage) {
        var outgoing = Messaging.handleOutgoingMessage(ctx, sendingMessage);
        ctx.sctpClient.send(ctx.streamNumber, Ngap.perEncode(outgoing.ngapPdu));
        FlowLogging.logSentMessage(outgoing);
    }

    public static IncomingMessage handleIncomingMessage(SimulationContext ctx, NGAP_PDU ngapPdu) {
        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
        var nasMessage = NgapInternal.extractNasMessage(ngapPdu);
        var decryptedNasMessage = NasSecurity.decryptNasMessage(ctx, nasMessage);
        var incomingMessage = new IncomingMessage(ngapPdu, ngapMessage, decryptedNasMessage);

        // check for AMF-UE-NGAP-ID
        {
            var ieAmfUeNgapId = NgapInternal.extractProtocolIe(ngapMessage, AMF_UE_NGAP_ID.class);
            if (ieAmfUeNgapId.size() > 0) {
                var ie = ieAmfUeNgapId.get(ieAmfUeNgapId.size() - 1);
                ctx.amfUeNgapId = ie.value;
            }
        }

        return incomingMessage;
    }

    public static OutgoingMessage handleOutgoingMessage(SimulationContext ctx, SendingMessage sendingMessage) {
        // Adding NAS PDU (if any)
        NasMessage securedNas = NasSecurity.encryptNasMessage(ctx, sendingMessage.nasMessage);
        if (securedNas != null) {
            // NOTE: criticality is hardcoded here, it may be changed
            sendingMessage.ngapBuilder.addNasPdu(securedNas, NgapCriticality.REJECT);
        }

        // Adding AMF-UE-NGAP-ID (if any)
        {
            Long amfUeNgapId = ctx.amfUeNgapId;
            if (amfUeNgapId != null) {
                // NOTE: criticality is hardcoded here, it may be changed
                sendingMessage.ngapBuilder.addAmfUeNgapId(amfUeNgapId, NgapCriticality.IGNORE);
            }
        }

        // Adding RAN-UE-NGAP-ID
        {
            // NOTE: criticality is hardcoded here, it may be changed
            sendingMessage.ngapBuilder.addRanUeNgapId(ctx.ranUeNgapId, NgapCriticality.REJECT);
        }

        // Adding user location information
        {
            // NOTE: criticality is hardcoded here, it may be changed
            sendingMessage.ngapBuilder.addUserLocationInformationNR(ctx.userLocationInformationNr, NgapCriticality.IGNORE);
        }

        return new OutgoingMessage(sendingMessage.ngapBuilder.build(), sendingMessage.nasMessage, securedNas);
    }

    public static void handleNasMessage(SimulationContext ctx, NasMessage message) {
        if (message instanceof AuthenticationRequest) {
            UeAuthentication.handleAuthenticationRequest(ctx, (AuthenticationRequest) message);
        } else if (message instanceof AuthenticationResult) {
            UeAuthentication.handleAuthenticationResult(ctx, (AuthenticationResult) message);
        } else if (message instanceof RegistrationReject) {
            UeRegistration.handleRegistrationReject(ctx, (RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            UeIdentity.handleIdentityRequest(ctx, (IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            UeRegistration.handleRegistrationAccept(ctx, (RegistrationAccept) message);
        } else if (message instanceof SecurityModeCommand) {
            UeSecurity.handleSecurityModeCommand(ctx, (SecurityModeCommand) message);
        } else {
            FlowLogging.logUnhandledMessage(message);
        }
    }
}
