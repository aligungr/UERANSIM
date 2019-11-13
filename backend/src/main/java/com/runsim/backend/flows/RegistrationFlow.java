package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.NGAP;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.enums.*;
import com.runsim.backend.nas.impl.ies.IE5gsRegistrationType;
import com.runsim.backend.nas.impl.ies.IEImsiMobileIdentity;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;
import com.runsim.backend.nas.impl.ies.IEUeSecurityCapability;
import com.runsim.backend.nas.impl.messages.AuthenticationRequest;
import com.runsim.backend.nas.impl.messages.RegistrationRequest;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.ngap.Values;
import com.runsim.backend.ngap.ngap_commondatatypes.Criticality;
import com.runsim.backend.ngap.ngap_commondatatypes.ProcedureCode;
import com.runsim.backend.ngap.ngap_commondatatypes.ProtocolIE_ID;
import com.runsim.backend.ngap.ngap_ies.*;
import com.runsim.backend.ngap.ngap_pdu_contents.DownlinkNASTransport;
import com.runsim.backend.ngap.ngap_pdu_contents.InitialContextSetupRequest;
import com.runsim.backend.ngap.ngap_pdu_contents.InitialUEMessage;
import com.runsim.backend.ngap.ngap_pdu_descriptions.InitiatingMessage;
import com.runsim.backend.ngap.ngap_pdu_descriptions.NGAP_PDU;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.ConsoleColors;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.Bit3;
import com.runsim.backend.utils.octets.Octet;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFlow extends BaseFlow {

    private static final int RAN_UE_NGAP_ID = 1;

    public State main(Message message) throws Exception {
        return sendRegistrationRequest();
    }

    private State sendRegistrationRequest() throws Exception {
        byte[] nasByteArray;
        RegistrationRequest nasMessage;

        NGAP_PDU ngapPdu;

        /* Create NAS PDU */
        {
            nasMessage = new RegistrationRequest();
            nasMessage.messageType = EMessageType.REGISTRATION_REQUEST;
            nasMessage.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
            nasMessage.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
            nasMessage.registrationType = new IE5gsRegistrationType();
            nasMessage.registrationType.followOnRequestPending = EFollowOnRequest.NO_FOR_PENDING;
            nasMessage.registrationType.registrationType = ERegistrationType.INITIAL_REGISTRATION;
            nasMessage.nasKeySetIdentifier = new IENasKeySetIdentifier();
            nasMessage.nasKeySetIdentifier.tsc = ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT;
            nasMessage.nasKeySetIdentifier.nasKeySetIdentifier = new Bit3(7);

            var imsi = new IEImsiMobileIdentity();
            imsi.mcc = EMobileCountryCode.unknownValue(1);
            imsi.mnc = EMobileNetworkCode3.unknownValue(imsi.mcc.value * 1000 + 1);
            imsi.routingIndicator = "0000";
            imsi.protectionSchemaId = EProtectionSchemeIdentifier.NULL_SCHEMA;
            imsi.schemaOutput = "000000001";
            imsi.homeNetworkPublicKeyIdentifier = new VHomeNetworkPki();
            imsi.homeNetworkPublicKeyIdentifier.value = new Octet(0);
            nasMessage.mobileIdentity = imsi;

            var capability = new IEUeSecurityCapability();
            capability.supported_5G_EA0 = new Bit(1);
            capability.supported_5G_IA0 = new Bit(1);
            capability.supported_EEA0 = new Bit(1);
            capability.supported_EIA0 = new Bit(1);

            capability.supported_128_5G_EA1 = new Bit(0);
            capability.supported_128_5G_EA2 = new Bit(0);
            capability.supported_128_5G_EA3 = new Bit(0);
            capability.supported_5G_EA4 = new Bit(0);
            capability.supported_5G_EA5 = new Bit(0);
            capability.supported_5G_EA6 = new Bit(0);
            capability.supported_5G_EA7 = new Bit(0);
            capability.supported_128_5G_IA1 = new Bit(0);
            capability.supported_128_5G_IA2 = new Bit(0);
            capability.supported_128_5G_IA3 = new Bit(0);
            capability.supported_5G_IA4 = new Bit(0);
            capability.supported_5G_IA5 = new Bit(0);
            capability.supported_5G_IA6 = new Bit(0);
            capability.supported_5G_IA7 = new Bit(0);
            capability.supported_128_EEA1 = new Bit(0);
            capability.supported_128_EEA2 = new Bit(0);
            capability.supported_128_EEA3 = new Bit(0);
            capability.supported_EEA4 = new Bit(0);
            capability.supported_EEA5 = new Bit(0);
            capability.supported_EEA6 = new Bit(0);
            capability.supported_EEA7 = new Bit(0);
            capability.supported_128_EIA1 = new Bit(0);
            capability.supported_128_EIA2 = new Bit(0);
            capability.supported_128_EIA3 = new Bit(0);
            capability.supported_EIA4 = new Bit(0);
            capability.supported_EIA5 = new Bit(0);
            capability.supported_EIA6 = new Bit(0);
            capability.supported_EIA7 = new Bit(0);
            nasMessage.ueSecurityCapability = capability;

            nasByteArray = NasEncoder.nasPdu(nasMessage);
        }

        /* Create NGAP PDU */
        {
            var ranUeNgapId = new InitialUEMessage.ProtocolIEs.SEQUENCE();
            ranUeNgapId.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
            ranUeNgapId.criticality = new Criticality(Criticality.ASN_reject);
            ranUeNgapId.value = new OpenTypeValue(new RAN_UE_NGAP_ID(RAN_UE_NGAP_ID));

            var nasPdu = new InitialUEMessage.ProtocolIEs.SEQUENCE();
            nasPdu.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
            nasPdu.criticality = new Criticality(Criticality.ASN_reject);
            nasPdu.value = new OpenTypeValue(new NAS_PDU(nasByteArray));

            var userLocationInformationNr = new UserLocationInformationNR();
            userLocationInformationNr.nR_CGI = new NR_CGI();
            userLocationInformationNr.nR_CGI.pLMNIdentity = new PLMNIdentity(new byte[]{0x00, 0x01, 0x10});
            userLocationInformationNr.nR_CGI.nRCellIdentity = new NRCellIdentity(new byte[]{0x00, 0x00, 0x01, 0x10, 0x00}, 36);
            userLocationInformationNr.tAI = new TAI();
            userLocationInformationNr.tAI.tAC = new TAC(new byte[]{0x00, 0x00, 0x75});
            userLocationInformationNr.tAI.pLMNIdentity = new PLMNIdentity(new byte[]{0x00, 0x01, 0x10});

            var userLocationInformation = new InitialUEMessage.ProtocolIEs.SEQUENCE();
            userLocationInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
            userLocationInformation.criticality = new Criticality(Criticality.ASN_reject);
            userLocationInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, userLocationInformationNr));

            var establishmentCause = new InitialUEMessage.ProtocolIEs.SEQUENCE();
            establishmentCause.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RRCEstablishmentCause);
            establishmentCause.criticality = new Criticality(Criticality.ASN_ignore);
            establishmentCause.value = new OpenTypeValue(new RRCEstablishmentCause(RRCEstablishmentCause.ASN_mo_Signalling));

            var protocolIEs = new ArrayList<InitialUEMessage.ProtocolIEs.SEQUENCE>();
            protocolIEs.add(ranUeNgapId);
            protocolIEs.add(nasPdu);
            protocolIEs.add(userLocationInformation);
            protocolIEs.add(establishmentCause);

            var initialUEMessage = new InitialUEMessage();
            initialUEMessage.protocolIEs = new InitialUEMessage.ProtocolIEs(protocolIEs);

            var initiatingMessage = new InitiatingMessage();
            initiatingMessage.procedureCode = new ProcedureCode(Values.NGAP_Constants__id_InitialUEMessage);
            initiatingMessage.criticality = new Criticality(Criticality.ASN_ignore);
            initiatingMessage.value = new OpenTypeValue(initialUEMessage);

            ngapPdu = new NGAP_PDU(NGAP_PDU.ASN_initiatingMessage, initiatingMessage);
        }

        Console.printDiv();
        Console.println(ConsoleColors.BLUE, "Sending Initial UE Message:");
        Console.println(ConsoleColors.WHITE_BRIGHT, NGAP.xerEncode(ngapPdu));
        Console.println(ConsoleColors.BLUE, "Where NAS PDU is:");
        Console.println(ConsoleColors.WHITE_BRIGHT, Json.toJson(nasMessage));

        sendPDU(ngapPdu);
        Console.printDiv();
        Console.println(ConsoleColors.BLUE, "PDU Sent, waiting for AMF Request Messages");

        return this::waitingAMFRequests;
    }

    private State waitingAMFRequests(Message message) {
        var pdu = message.getAsPDU();

        Console.printDiv();
        Console.println(ConsoleColors.BLUE, "Message received from AMF with length", message.getLength(), "bytes.");
        Console.println(ConsoleColors.BLUE, "Received NGAP PDU:");
        Console.println(ConsoleColors.WHITE_BRIGHT, NGAP.xerEncode(pdu));

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            Console.println(ConsoleColors.YELLOW, "bad message from AMF, InitiatingMessage is expected. message ignored");
            return this::waitingAMFRequests;
        }

        var initiatingMessage = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

        if (initiatingMessage instanceof DownlinkNASTransport) {
            var downlinkNASTransport = (DownlinkNASTransport) initiatingMessage;
            return handleDownlinkNASTransport(downlinkNASTransport);
        } else if (initiatingMessage instanceof InitialContextSetupRequest) {
            var initialContextSetupRequest = (InitialContextSetupRequest) initiatingMessage;
            return handleInitialContextSetupRequest(initialContextSetupRequest);
        } else {
            Console.println(ConsoleColors.YELLOW, "bad message from AMF, DownlinkNASTransport or InitialContextSetupRequest is expected. message ignored");
            return this::waitingAMFRequests;
        }
    }

    private State handleDownlinkNASTransport(DownlinkNASTransport message) {
        Console.println(ConsoleColors.BLUE, "DownlinkNASTransport is handling.");

        var protocolIEs = (List<DownlinkNASTransport.ProtocolIEs.SEQUENCE>) message.protocolIEs.valueList;

        NAS_PDU nasPayload = null;
        for (var protocolIE : protocolIEs) {
            if (protocolIE.value.getDecodedValue() instanceof NAS_PDU) {
                nasPayload = (NAS_PDU) protocolIE.value.getDecodedValue();
                break;
            }
        }

        if (nasPayload == null) {
            Console.printDiv();
            Console.println(ConsoleColors.RED, "bad message from AMF, NAS PDU was expected.");
            Console.println(ConsoleColors.RED, "closing connection");
            return closeConnection();
        }

        var nasMessage = NasDecoder.nasPdu(nasPayload.getValue());

        Console.println(ConsoleColors.BLUE, "Where NAS PDU is:");
        Console.println(ConsoleColors.WHITE_BRIGHT, Json.toJson(nasMessage));
        return handleNasMessage(nasMessage);
    }

    private State handleInitialContextSetupRequest(InitialContextSetupRequest message) {
        Console.printDiv();
        Console.println(ConsoleColors.BLUE, "InitialContextSetupRequest is handling.");
        Console.println(ConsoleColors.BLUE, "Received NGAP PDU:");
        Console.println(ConsoleColors.WHITE_BRIGHT, NGAP.xerEncode(message));

        Console.println(ConsoleColors.RED, "this method is not implemented yet.");
        Console.println(ConsoleColors.RED, "closing connection");

        return closeConnection();
    }

    private State handleNasMessage(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(ConsoleColors.BLUE, "NAS message is handling.");

        if (!(nasMessage instanceof PlainNasMessage)) {
            Console.println(ConsoleColors.RED, "Security protected NAS messages are not implemented yet");
            Console.println(ConsoleColors.RED, "Closing connection");
            return closeConnection();
        }

        var message = (PlainNasMessage) nasMessage;

        Console.println(ConsoleColors.BLUE, message.messageType.name, "is detected");
        if (message instanceof AuthenticationRequest) {
            return handleAuthenticationRequest((AuthenticationRequest) message);
        } else if (message.messageType.equals(EMessageType.AUTHENTICATION_REJECT)) {
            Console.println(ConsoleColors.RED, "AUTHENTICATION_REJECT not implemented yet");
            Console.println(ConsoleColors.RED, "Closing connection");
        } else {
            Console.println(ConsoleColors.RED, "This message type was not implemented yet");
            Console.println(ConsoleColors.RED, "Closing connection");
        }

        return closeConnection();
    }

    private State handleAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        Console.printDiv();
        Console.println(ConsoleColors.BLUE, "AuthenticationRequest is handling.");
        Console.println(ConsoleColors.RED, "But but it was not implemented yet");
        Console.println(ConsoleColors.RED, "Closing connection");
        return closeConnection();
    }
}
