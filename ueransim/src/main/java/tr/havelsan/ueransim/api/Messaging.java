/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.*;
import tr.havelsan.ueransim.api.gnb.GnbContextManagement;
import tr.havelsan.ueransim.api.ue.mm.*;
import tr.havelsan.ueransim.api.ue.sm.UePduSessionEstablishment;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapInternal;

public class Messaging {

    public static void send(SimulationContext ctx, SendingMessage sendingMessage) {
        var outgoing = Messaging.handleOutgoingMessage(ctx, sendingMessage);
        ctx.sctpClient.send(ctx.streamNumber, Ngap.perEncode(outgoing.ngapPdu));
        FlowLogging.logSentMessage(outgoing);
        ctx.dispatchMessageSent(outgoing);
    }

    public static IncomingMessage handleIncomingMessage(SimulationContext ctx, NGAP_PDU ngapPdu) {
        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
        var nasMessage = NgapInternal.extractNasMessage(ngapPdu);
        var decryptedNasMessage = NasSecurity.decryptNasMessage(ctx.currentNsc, nasMessage);
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
        NasMessage securedNas = NasSecurity.encryptNasMessage(ctx.currentNsc, sendingMessage.nasMessage);
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
            sendingMessage.ngapBuilder.addUserLocationInformationNR(ctx.ueConfig.userLocationInformationNr, NgapCriticality.IGNORE);
        }

        return new OutgoingMessage(sendingMessage.ngapBuilder.build(), sendingMessage.nasMessage, securedNas);
    }

    public static void handleNgapMessage(SimulationContext ctx, IncomingMessage message) {
        if (message.ngapMessage instanceof InitialContextSetupRequest) {
            GnbContextManagement.handleInitialContextSetup(ctx, (InitialContextSetupRequest) message.ngapMessage);
        }

        var nasMessage = message.getNasMessage(NasMessage.class);
        if (nasMessage != null) {
            Messaging.handleNasMessage(ctx, nasMessage);
        } else {
            FlowLogging.logUnhandledMessage(message);
        }
    }

    public static void handleNasMessage(SimulationContext ctx, NasMessage message) {
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
