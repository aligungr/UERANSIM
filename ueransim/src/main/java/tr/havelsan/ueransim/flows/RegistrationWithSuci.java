package tr.havelsan.ueransim.flows;

import threegpp.milenage.Milenage;
import threegpp.milenage.MilenageBufferFactory;
import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBuffer;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.Message;
import tr.havelsan.ueransim.Ngap;
import tr.havelsan.ueransim.app.Json;
import tr.havelsan.ueransim.app.ue.UeUtils;
import tr.havelsan.ueransim.nas.EapEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.eap.*;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.nas.impl.values.VHomeNetworkPki;
import tr.havelsan.ueransim.nas.impl.values.VSliceDifferentiator;
import tr.havelsan.ueransim.nas.impl.values.VSliceServiceType;
import tr.havelsan.ueransim.ngap.ngap_ies.AMF_UE_NGAP_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.DownlinkNASTransport;
import tr.havelsan.ueransim.ngap.ngap_pdu_contents.ErrorIndication;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistrationWithSuci extends BaseFlow {

    private MilenageBufferFactory<BigIntegerBuffer> milenageBufferFactory;
    private long amfUeNgapId;
    private long ranUeNgapId = 1;

    private static int findEapLength(Eap eap) {
        var stream = new OctetOutputStream();
        EapEncoder.eapPdu(stream, eap);
        return stream.length();
    }

    @Override
    public State main(Message message) throws Exception {
        this.milenageBufferFactory = BigIntegerBufferFactory.getInstance();
        return sendRegistrationRequest(message);
    }

    private State sendRegistrationRequest(Message message) {
        var registrationRequest = new RegistrationRequest();
        registrationRequest.registrationType = new IE5gsRegistrationType(IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING, IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);
        registrationRequest.nasKeySetIdentifier = new IENasKeySetIdentifier(IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, new Bit3(3));
        registrationRequest.requestedNSSAI = new IENssai(new IESNssai[]{
                new IESNssai(new VSliceServiceType(1), new VSliceDifferentiator("09AFaf"), null, null)
        });

        var mobileIdentity = new IEImsiMobileIdentity();
        mobileIdentity.mcc = EMccValue.fromValue(123);
        mobileIdentity.mnc = EMncValue.fromValue(123);
        mobileIdentity.routingIndicator = "0000";
        mobileIdentity.protectionSchemaId = IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEME;
        mobileIdentity.homeNetworkPublicKeyIdentifier = new VHomeNetworkPki(0);
        mobileIdentity.schemeOutput = "000000099";
        registrationRequest.mobileIdentity = mobileIdentity;

        var ngapPdu = UeUtils.createInitialUeMessage(registrationRequest, ranUeNgapId, RRCEstablishmentCause.ASN_mo_Data);

        sendPDU(ngapPdu);

        return this::waitAmfRequests;
    }

    private State waitAmfRequests(Message message) {
        var pdu = message.getAsPDU();

        Console.printDiv();
        Console.println(Color.BLUE, "Message received from AMF with length", message.getLength(), "bytes.");
        Console.println(Color.BLUE, "Received NGAP PDU:");
        Console.println(Color.WHITE_BRIGHT, Ngap.xerEncode(pdu));

        if (!(pdu.getValue() instanceof InitiatingMessage)) {
            Console.println(Color.YELLOW, "bad message from AMF, InitiatingMessage is expected. message ignored");
            return this::waitAmfRequests;
        }

        var initiatingMessage = ((InitiatingMessage) pdu.getValue()).value.getDecodedValue();

        if (initiatingMessage instanceof DownlinkNASTransport) {
            var downlinkNASTransport = (DownlinkNASTransport) initiatingMessage;
            for (var item : downlinkNASTransport.protocolIEs.valueList) {
                var protocolIE = (DownlinkNASTransport.ProtocolIEs.SEQUENCE) item;
                if (protocolIE.value.getDecodedValue() instanceof AMF_UE_NGAP_ID) {
                    var amfUeNgapId = (AMF_UE_NGAP_ID) protocolIE.value.getDecodedValue();
                    this.amfUeNgapId = amfUeNgapId.value;
                }
            }
            return handleDownlinkNASTransport(downlinkNASTransport);
        } else if (initiatingMessage instanceof ErrorIndication) {
            Console.println(Color.RED, "Error indication received");
            Console.println(Color.RED, "Closing connection");
            return closeConnection();
        } else {
            Console.println(Color.YELLOW, "bad message from AMF, DownlinkNASTransport or InitialContextSetupRequest is expected. message ignored");
            return this::waitAmfRequests;
        }
    }

    private State handleDownlinkNASTransport(DownlinkNASTransport message) {
        var nasMessage = UeUtils.getNasMessage(message);
        if (nasMessage == null) {
            Console.printDiv();
            Console.println(Color.RED, "bad message from AMF, NAS PDU was expected.");
            Console.println(Color.RED, "closing connection");
            return closeConnection();
        }

        Console.println(Color.BLUE, "Where NAS PDU is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
        return handleNasMessage(nasMessage);
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
            Console.println(Color.RED, "This message type was not implemented yet");
            Console.println(Color.RED, "Closing connection");
            //return handleAuthenticationResult((AuthenticationResult) message);
        } else if (message instanceof RegistrationReject) {
            Console.println(Color.RED, "This message type was not implemented yet");
            Console.println(Color.RED, "Closing connection");
            //return handleRegistrationReject((RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            Console.println(Color.RED, "This message type was not implemented yet");
            Console.println(Color.RED, "Closing connection");
            //return handleIdentityRequest((IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            Console.println(Color.RED, "This message type was not implemented yet");
            Console.println(Color.RED, "Closing connection");
            //return handleRegistrationAccept((RegistrationAccept) message);
        } else {
            Console.println(Color.RED, "This message type was not implemented yet");
            Console.println(Color.RED, "Closing connection");
        }

        return closeConnection();
    }

    private State handleAuthenticationRequest(AuthenticationRequest message) {
        Console.printDiv();
        Console.println(Color.BLUE, "AuthenticationRequest is handling.");

        OctetString mac;
        byte[] res;
        Octet id;

        // Handle request
        {
            var akaPrime = (EapAkaPrime) message.eapMessage.eap;
            var rand = akaPrime.attributes.get(EEapAkaAttributeType.AT_RAND);
            mac = akaPrime.attributes.get(EEapAkaAttributeType.AT_MAC);
            id = akaPrime.id;

            final String OP = "c9e8763286b5b9ffbdf56e1297d0887b";
            final String SQN = "16f3b3f70fc2";
            final String AMF = "c3ab";
            final String KEY = "5122250214c33e723a5dd523fc145fc0";

            byte[] op = Utils.hexStringToByteArray(OP);
            byte[] sqn = Utils.hexStringToByteArray(SQN);
            byte[] amf = Utils.hexStringToByteArray(AMF);

            var cipher = Ciphers.createRijndaelCipher(Utils.hexStringToByteArray(KEY));

            byte[] opc = Milenage.calculateOPc(op, cipher, milenageBufferFactory);

            Milenage<BigIntegerBuffer> milenage = new Milenage<>(opc, cipher, milenageBufferFactory);
            ExecutorService executorService = Executors.newCachedThreadPool();

            Map<MilenageResult, byte[]> result;
            try {
                result = milenage.calculateAll(rand.toByteArray(), sqn, amf, executorService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            executorService.shutdown();
            res = result.get(MilenageResult.RES);
        }

        // Send response
        {
            var response = new AuthenticationResponse();
            response.authenticationResponseParameter = new IEAuthenticationResponseParameter(new OctetString("0102030405060708090A0B0C0D0E0F10"));

            var akaPrime = new EapAkaPrime();
            akaPrime.id = id;
            akaPrime.EAPType = EEapType.EAP_AKA_PRIME;
            akaPrime.length = new Octet2(0);
            akaPrime.subType = EEapAkaSubType.AKA_CHALLENGE;
            akaPrime.code = EEapCode.RESPONSE;
            akaPrime.attributes = new LinkedHashMap<>();
            akaPrime.attributes.put(EEapAkaAttributeType.AT_RES, new OctetString(res));
            akaPrime.attributes.put(EEapAkaAttributeType.AT_MAC, mac);

            int length = findEapLength(akaPrime);
            akaPrime.length = new Octet2(length);
            response.eapMessage = new IEEapMessage(akaPrime);

            Console.printDiv();
            Console.println(Color.BLUE, "Authentication Response will be sent to AMF");
            Console.println(Color.BLUE, "While NAS message is:");
            Console.println(Color.WHITE_BRIGHT, Json.toJson(response));
            Console.println(Color.BLUE, "While NGAP message is:");

            var ngap = UeUtils.createUplinkMessage(response, ranUeNgapId, amfUeNgapId);
            sendPDU(ngap);
        }

        return this::waitAmfRequests;
    }
}
