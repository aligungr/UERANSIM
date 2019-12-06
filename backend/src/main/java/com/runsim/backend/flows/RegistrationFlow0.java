package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.NGAP;
import com.runsim.backend.nas.NasDecoder;
import com.runsim.backend.nas.NasEncoder;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.eap.*;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode3;
import com.runsim.backend.nas.impl.ies.*;
import com.runsim.backend.nas.impl.messages.*;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.ngap.Values;
import com.runsim.backend.ngap.ngap_commondatatypes.Criticality;
import com.runsim.backend.ngap.ngap_commondatatypes.ProcedureCode;
import com.runsim.backend.ngap.ngap_commondatatypes.ProtocolIE_ID;
import com.runsim.backend.ngap.ngap_ies.*;
import com.runsim.backend.ngap.ngap_pdu_contents.*;
import com.runsim.backend.ngap.ngap_pdu_descriptions.InitiatingMessage;
import com.runsim.backend.ngap.ngap_pdu_descriptions.NGAP_PDU;
import com.runsim.backend.utils.Color;
import com.runsim.backend.utils.Console;
import com.runsim.backend.utils.Json;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.Bit3;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet2;
import com.runsim.backend.utils.octets.OctetString;
import fr.marben.asnsdk.japi.InvalidStructureException;
import fr.marben.asnsdk.japi.spe.OpenTypeValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class RegistrationFlow0 extends BaseFlow {

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
            nasMessage.registrationType = new IE5gsRegistrationType();
            nasMessage.registrationType.followOnRequestPending = IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING;
            nasMessage.registrationType.registrationType = IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION;
            nasMessage.nasKeySetIdentifier = new IENasKeySetIdentifier();
            nasMessage.nasKeySetIdentifier.tsc = IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT;
            nasMessage.nasKeySetIdentifier.nasKeySetIdentifier = new Bit3(7);

            var imsi = new IEImsiMobileIdentity();
            imsi.mcc = EMobileCountryCode.unknownValue(1);
            imsi.mnc = EMobileNetworkCode3.unknownValue(imsi.mcc.intValue() * 1000 + 1);
            imsi.routingIndicator = "0000";
            imsi.protectionSchemaId = IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEMA;
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
            ranUeNgapId.value = new OpenTypeValue(new RAN_UE_NGAP_ID(1));

            var nasPdu = new InitialUEMessage.ProtocolIEs.SEQUENCE();
            nasPdu.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
            nasPdu.criticality = new Criticality(Criticality.ASN_reject);
            nasPdu.value = new OpenTypeValue(new NAS_PDU(nasByteArray));

            var userLocationInformation = new InitialUEMessage.ProtocolIEs.SEQUENCE();
            userLocationInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
            userLocationInformation.criticality = new Criticality(Criticality.ASN_reject);
            userLocationInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, createUserLocationInformationNr()));

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
        Console.println(Color.BLUE, "Sending Initial UE Message:");
        Console.println(Color.WHITE_BRIGHT, NGAP.xerEncode(ngapPdu));
        Console.println(Color.BLUE, "Where NAS PDU is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));

        sendPDU(ngapPdu);
        Console.printDiv();
        Console.println(Color.BLUE, "PDU Sent, waiting for AMF Request Messages");

        return this::waitingAMFRequests;
    }

    private State waitingAMFRequests(Message message) {
        var pdu = message.getAsPDU();

        Console.printDiv();
        Console.println(Color.BLUE, "Message received from AMF with length", message.getLength(), "bytes.");
        Console.println(Color.BLUE, "Received NGAP PDU:");
        Console.println(Color.WHITE_BRIGHT, NGAP.xerEncode(pdu));

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            Console.println(Color.YELLOW, "bad message from AMF, InitiatingMessage is expected. message ignored");
            return this::waitingAMFRequests;
        }

        var initiatingMessage = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

        if (initiatingMessage instanceof DownlinkNASTransport) {
            var downlinkNASTransport = (DownlinkNASTransport) initiatingMessage;
            return handleDownlinkNASTransport(downlinkNASTransport);
        } else if (initiatingMessage instanceof InitialContextSetupRequest) {
            var initialContextSetupRequest = (InitialContextSetupRequest) initiatingMessage;
            return handleInitialContextSetupRequest(initialContextSetupRequest);
        } else if (initiatingMessage instanceof ErrorIndication) {
            Console.println(Color.RED, "Error indication received");
            Console.println(Color.RED, "Closing connection");
            return closeConnection();
        } else {
            Console.println(Color.YELLOW, "bad message from AMF, DownlinkNASTransport or InitialContextSetupRequest is expected. message ignored");
            return this::waitingAMFRequests;
        }
    }

    private State handleDownlinkNASTransport(DownlinkNASTransport message) {
        Console.println(Color.BLUE, "DownlinkNASTransport is handling.");

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
            Console.println(Color.RED, "bad message from AMF, NAS PDU was expected.");
            Console.println(Color.RED, "closing connection");
            return closeConnection();
        }

        var nasMessage = NasDecoder.nasPdu(nasPayload.getValue());

        Console.println(Color.BLUE, "Where NAS PDU is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
        return handleNasMessage(nasMessage);
    }

    private State handleInitialContextSetupRequest(InitialContextSetupRequest message) {
        Console.printDiv();
        Console.println(Color.BLUE, "InitialContextSetupRequest is handling.");

        Console.println(Color.RED, "this method is not implemented yet.");
        Console.println(Color.RED, "closing connection");

        return closeConnection();
    }

    private State handleNasMessage(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, "NAS message is handling.");

        if (!(nasMessage instanceof PlainMmMessage)) {
            Console.println(Color.RED, "Security protected NAS messages are not implemented yet");
            Console.println(Color.RED, "Closing connection");
            return closeConnection();
        }

        var message = (PlainMmMessage) nasMessage;

        Console.println(Color.BLUE, message.messageType.name(), "is detected");

        if (message instanceof AuthenticationRequest) {
            return handleAuthenticationRequest((AuthenticationRequest) message);
        } else if (message instanceof AuthenticationResult) {
            return handleAuthenticationResult((AuthenticationResult) message);
        } else if (message instanceof RegistrationReject) {
            return handleRegistrationReject((RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            return handleIdentityRequest((IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            return handleRegistrationAccept((RegistrationAccept) message);
        } else {
            Console.println(Color.RED, "This message type was not implemented yet");
            Console.println(Color.RED, "Closing connection");
        }

        return closeConnection();
    }

    private State handleRegistrationAccept(RegistrationAccept message) {
        Console.printDiv();
        Console.println(Color.GREEN, "RegistrationAccept is handling.");

        var registrationComplete = new RegistrationComplete();

        Console.printDiv();
        Console.println(Color.BLUE, "Registration Complete will be sent to AMF");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(registrationComplete));
        Console.println(Color.BLUE, "While NGAP message is:");

        var userLocInformation = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        userLocInformation.criticality = new Criticality(Criticality.ASN_ignore);
        userLocInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        try {
            userLocInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, createUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        var uplink = createUplinkMessage(registrationComplete, userLocInformation);

        Console.println(Color.WHITE_BRIGHT, NGAP.xerEncode(uplink));

        sendPDU(uplink);

        Console.printDiv();
        Console.println(Color.GREEN_BOLD, "Registration complete.");
        return this::waitingAMFRequests;
    }

    private State handleIdentityRequest(IdentityRequest identityRequest) {
        Console.printDiv();
        Console.println(Color.BLUE, "IdentityRequest is handling.");

        var identityType = identityRequest.identityType.value;

        var identityResponse = new IdentityResponse();

        IE5gsMobileIdentity mobileIdentity;
        if (identityType.equals(EIdentityType.IMEI)) {
            var imei = new IEImeiMobileIdentity();
            imei.imei = "100000000000001";
            mobileIdentity = imei;
        } else {
            Console.println(Color.RED, "Identity type not implemented yet: " + identityType.name());
            Console.println(Color.RED, "Closing connection");
            return closeConnection();
        }
        identityResponse.mobileIdentity = mobileIdentity;

        Console.printDiv();
        Console.println(Color.BLUE, "Identity Response will be sent to AMF");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(identityResponse));
        Console.println(Color.BLUE, "While NGAP message is:");

        var userLocInformation = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        userLocInformation.criticality = new Criticality(Criticality.ASN_ignore);
        userLocInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        try {
            userLocInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, createUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        var uplink = createUplinkMessage(identityResponse, userLocInformation);

        Console.println(Color.WHITE_BRIGHT, NGAP.xerEncode(uplink));

        sendPDU(uplink);

        Console.printDiv();
        Console.println(Color.BLUE, "PDU Sent, waiting for other AMF Request Messages");
        return this::waitingAMFRequests;
    }

    private State handleAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        Console.printDiv();
        Console.println(Color.BLUE, "AuthenticationRequest is handling.");

        var eap = new EapAkaPrime();
        eap.id = new Octet(1);
        eap.length = new Octet2(48);
        eap.EAPType = EEapType.EAP_AKA_PRIME;
        eap.code = EEapCode.RESPONSE;
        eap.subType = EEapAkaSubType.AKA_CHALLENGE;
        eap.attributes = new LinkedHashMap<>();
        eap.attributes.put(EEapAkaAttributeType.AT_RES, new OctetString("000864955b0fe729127b0000000000000000"));
        eap.attributes.put(EEapAkaAttributeType.AT_MAC, new OctetString("000069f5f2af9798323126ef3cf8896a8c4b"));

        var response = new AuthenticationResponse();
        response.eapMessage = new IEEapMessage();
        response.eapMessage.eap = eap;

        Console.printDiv();
        Console.println(Color.BLUE, "Authentication Response will be sent to AMF");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(response));
        Console.println(Color.BLUE, "While NGAP message is:");

        var userLocInformation = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        userLocInformation.criticality = new Criticality(Criticality.ASN_ignore);
        userLocInformation.id = new ProtocolIE_ID(Values.NGAP_Constants__id_UserLocationInformation);
        try {
            userLocInformation.value = new OpenTypeValue(new UserLocationInformation(UserLocationInformation.ASN_userLocationInformationNR, createUserLocationInformationNr()));
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
        var uplink = createUplinkMessage(response, userLocInformation);

        Console.println(Color.WHITE_BRIGHT, NGAP.xerEncode(uplink));

        sendPDU(uplink);

        Console.printDiv();
        Console.println(Color.BLUE, "PDU Sent, waiting for other AMF Request Messages");
        return this::waitingAMFRequests;
    }

    private State handleRegistrationReject(RegistrationReject registrationReject) {
        Console.printDiv();
        Console.println(Color.BLUE, "RegistrationReject is handling.");
        Console.println(Color.RED, "5G MM Cause:", registrationReject.mmCause.value);
        Console.println(Color.RED, "Closing connection");
        return closeConnection();
    }

    private NGAP_PDU createUplinkMessage(NasMessage nasMessage, UplinkNASTransport.ProtocolIEs.SEQUENCE... additionalProtocolIEs) {
        var list = new ArrayList<UplinkNASTransport.ProtocolIEs.SEQUENCE>();

        var uplink = new UplinkNASTransport();
        uplink.protocolIEs = new UplinkNASTransport.ProtocolIEs();
        uplink.protocolIEs.valueList = list;

        var amfUe = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        amfUe.id = new ProtocolIE_ID(Values.NGAP_Constants__id_AMF_UE_NGAP_ID);
        amfUe.criticality = new Criticality(Criticality.ASN_reject);
        amfUe.value = new OpenTypeValue(new AMF_UE_NGAP_ID(1));
        list.add(amfUe);

        var ranUe = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        ranUe.id = new ProtocolIE_ID(Values.NGAP_Constants__id_RAN_UE_NGAP_ID);
        ranUe.criticality = new Criticality(Criticality.ASN_reject);
        ranUe.value = new OpenTypeValue(new RAN_UE_NGAP_ID(1));
        list.add(ranUe);

        var nasPayload = new UplinkNASTransport.ProtocolIEs.SEQUENCE();
        nasPayload.id = new ProtocolIE_ID(Values.NGAP_Constants__id_NAS_PDU);
        nasPayload.criticality = new Criticality(Criticality.ASN_reject);
        nasPayload.value = new OpenTypeValue(new NAS_PDU(NasEncoder.nasPdu(nasMessage)));
        list.add(nasPayload);

        list.addAll(Arrays.asList(additionalProtocolIEs));

        var initiatingMessage = new InitiatingMessage();
        initiatingMessage.procedureCode = new ProcedureCode(Values.NGAP_Constants__id_UplinkNASTransport);
        initiatingMessage.criticality = new Criticality(Criticality.ASN_ignore);
        initiatingMessage.value = new OpenTypeValue(uplink);

        try {
            return new NGAP_PDU(NGAP_PDU.ASN_initiatingMessage, initiatingMessage);
        } catch (InvalidStructureException e) {
            throw new RuntimeException(e);
        }
    }

    private UserLocationInformationNR createUserLocationInformationNr() {
        var userLocationInformationNr = new UserLocationInformationNR();
        userLocationInformationNr.nR_CGI = new NR_CGI();
        userLocationInformationNr.nR_CGI.pLMNIdentity = new PLMNIdentity(new byte[]{0x00, 0x01, 0x10});
        userLocationInformationNr.nR_CGI.nRCellIdentity = new NRCellIdentity(new byte[]{0x00, 0x00, 0x01, 0x10, 0x00}, 36);
        userLocationInformationNr.tAI = new TAI();
        userLocationInformationNr.tAI.tAC = new TAC(new byte[]{0x00, 0x00, 0x75});
        userLocationInformationNr.tAI.pLMNIdentity = new PLMNIdentity(new byte[]{0x00, 0x01, 0x10});
        return userLocationInformationNr;
    }

    private State handleAuthenticationResult(AuthenticationResult message) {
        Console.printDiv();
        Console.println(Color.BLUE, "AuthenticationResult is handling.");

        if (message.eapMessage.eap.code.equals(EEapCode.SUCCESS))
            Console.println(Color.GREEN, "Authentication success");
        else if (message.eapMessage.eap.code.equals(EEapCode.FAILURE)) {
            Console.println(Color.RED, "Authentication failure");
            Console.println(Color.RED, "Closing connection");
            return closeConnection();
        }

        Console.println(Color.BLUE, "Waiting for other AMF Request Messages");
        return this::waitingAMFRequests;
    }
}
