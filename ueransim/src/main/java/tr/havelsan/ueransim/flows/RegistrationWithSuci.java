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
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.impl.enums.EIdentityType;
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
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistrationWithSuci extends BaseFlow {

    private MilenageBufferFactory<BigIntegerBuffer> milenageBufferFactory;
    private long amfUeNgapId;
    private long ranUeNgapId = 1;

    @Override
    public State main(Message message) {
        this.milenageBufferFactory = BigIntegerBufferFactory.getInstance();
        return sendRegistrationRequest();
    }

    private State sendRegistrationRequest() {
        var registrationRequest = new RegistrationRequest();
        registrationRequest.registrationType = new IE5gsRegistrationType(IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING, IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);
        registrationRequest.nasKeySetIdentifier = new IENasKeySetIdentifier(IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, new Bit3(3));
        registrationRequest.requestedNSSAI = new IENssai(new IESNssai[]{
                new IESNssai(new VSliceServiceType(1), new VSliceDifferentiator("09afaf"), null, null)
        });

        var mobileIdentity = new IEImsiMobileIdentity();
        mobileIdentity.mcc = EMccValue.fromValue(1);
        mobileIdentity.mnc = EMncValue.fromValue(1);
        mobileIdentity.routingIndicator = "0000";
        mobileIdentity.protectionSchemaId = IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEME;
        mobileIdentity.homeNetworkPublicKeyIdentifier = new VHomeNetworkPki(0);
        mobileIdentity.schemeOutput = "000000095";
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
            return handleAuthenticationResult((AuthenticationResult) message);
        } else if (message instanceof RegistrationReject) {
            return handleRegistrationReject((RegistrationReject) message);
        } else if (message instanceof IdentityRequest) {
            return handleIdentityRequest((IdentityRequest) message);
        } else if (message instanceof RegistrationAccept) {
            return handleRegistrationAccept((RegistrationAccept) message);
        } else {
            Console.println(Color.YELLOW, "This message type was not implemented yet: %s", message.getClass().getSimpleName());
            Console.println(Color.YELLOW, "Ignoring message");
        }

        return this::waitAmfRequests;
    }

    private State handleRegistrationReject(RegistrationReject message) {
        return closeConnection();
    }

    private State handleAuthenticationResult(AuthenticationResult message) {
        Console.println(Color.BLUE, "Authentication result received");
        return this::waitAmfRequests;
    }

    private State handleRegistrationAccept(RegistrationAccept message) {
        var response = new RegistrationComplete();

        sendUplinkMessage(response);

        return this::waitAmfRequests;
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
            var rand = akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_RAND);
            rand = new OctetString("67b566efc2a5afb8f153aef84959200d");
            mac = akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_MAC);
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
                result = milenage.calculateAll(rand.substring(0).toByteArray(), sqn, amf, executorService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            executorService.shutdown();
            res = result.get(MilenageResult.RES);

            int padding = (res.length + 2) % 4;
            byte[] paddedRes = new byte[res.length + padding];
            if (padding >= 0) System.arraycopy(res, 0, paddedRes, padding, res.length);
            res = paddedRes;
        }

        // Send response
        {
            var response = new AuthenticationResponse();
            response.authenticationResponseParameter = new IEAuthenticationResponseParameter(new OctetString("0102030405060708090A0B0C0D0E0F10"));

            var akaPrime = new EapAkaPrime(Eap.ECode.RESPONSE, id);
            akaPrime.subType = EapAkaPrime.ESubType.AKA_CHALLENGE;
            akaPrime.attributes = new LinkedHashMap<>();
            akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_RES, new OctetString(res));
            akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_MAC, mac);
            response.eapMessage = new IEEapMessage(akaPrime);

            sendUplinkMessage(response);
        }

        return this::waitAmfRequests;
    }

    private State handleIdentityRequest(IdentityRequest message) {
        if (!message.identityType.value.equals(EIdentityType.IMEI)) {
            Console.println(Color.RED, "Identity request for %s is not implemented yet", message.identityType.value.name());
            return this::waitAmfRequests;
        }

        var response = new IdentityResponse();
        response.mobileIdentity = new IEImeiMobileIdentity("356938035643809");

        sendUplinkMessage(response);

        return this::waitAmfRequests;
    }

    private void sendUplinkMessage(NasMessage nas) {
        logNasMessageWillSend(nas);
        var ngap = UeUtils.createUplinkMessage(nas, ranUeNgapId, amfUeNgapId);
        sendPDU(ngap);
        logMessageSent();
    }

    private void logNasMessageWillSend(NasMessage nasMessage) {
        Console.printDiv();
        Console.println(Color.BLUE, nasMessage.getClass().getSimpleName() + "will be sent to AMF");
        Console.println(Color.BLUE, "While NAS message is:");
        Console.println(Color.WHITE_BRIGHT, Json.toJson(nasMessage));
    }

    private void logMessageSent() {
        Console.println(Color.BLUE, "Message sent:");
    }
}
