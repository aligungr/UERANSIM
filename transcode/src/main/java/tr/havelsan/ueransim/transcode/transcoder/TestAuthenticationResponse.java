package tr.havelsan.ueransim.transcode.transcoder;

import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationResponse;
import tr.havelsan.ueransim.transcode.TranscoderTesting;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;

public class TestAuthenticationResponse extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e005778003002010030320100000305000864955b0fe729127b00000000000000000b05000069f5f2af9798323126ef3cf8896a8c4b";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, AuthenticationResponse.class);
        var mes = (AuthenticationResponse) message;
        assertEquals(mes.messageType, EMessageType.AUTHENTICATION_RESPONSE);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        assertNull(mes.authenticationResponseParameter);
        assertNotNull(mes.eapMessage);
        assertNotNull(mes.eapMessage.eap);

        assertInstance(mes.eapMessage.eap, EapAkaPrime.class);
        assertEquals(mes.eapMessage.eap.code, Eap.ECode.RESPONSE);
        assertEquals(mes.eapMessage.eap.id, 1);
        assertEquals(mes.eapMessage.eap.EAPType, Eap.EEapType.EAP_AKA_PRIME);

        var akaPrime = (EapAkaPrime) mes.eapMessage.eap;
        assertEquals(akaPrime.subType, EapAkaPrime.ESubType.AKA_CHALLENGE);
        assertEquals(akaPrime.attributes.size(), 2);
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_RES), new OctetString("000864955b0fe729127b0000000000000000"));
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_MAC), new OctetString("000069f5f2af9798323126ef3cf8896a8c4b"));
    }

    @Override
    public NasMessage getMessage() {
        var mes = new AuthenticationResponse();
        mes.messageType = EMessageType.AUTHENTICATION_RESPONSE;
        mes.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        mes.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;

        mes.authenticationResponseParameter = null;
        mes.eapMessage = new IEEapMessage();
        var akaPrime = new EapAkaPrime(Eap.ECode.RESPONSE, new Octet(1));
        mes.eapMessage.eap = akaPrime;

        akaPrime.subType = EapAkaPrime.ESubType.AKA_CHALLENGE;
        akaPrime.attributes = new LinkedHashMap<>();
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_RES, new OctetString("000864955b0fe729127b0000000000000000"));
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_MAC, new OctetString("000069f5f2af9798323126ef3cf8896a8c4b"));

        return mes;
    }
}
