package tr.havelsan.ueransim.transcode.transcoder;

import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IEAbba;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationRequest;
import tr.havelsan.ueransim.transcode.TranscoderTesting;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;

public class TestAuthenticationRequest extends TranscoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e00560002000078006c0101006c3201000001050000c1c855df1555ab38342f5e5242e286b202050000adca1f49a09c8000167c4316a3a016d1180100011709002035473a6d6e633030312e6d63633030312e336770706e6574776f726b2e6f72670b0500005addcf552b22f2909f7dde0050e22cbd";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, AuthenticationRequest.class);
        var mes = (AuthenticationRequest) message;
        assertEquals(mes.messageType, EMessageType.AUTHENTICATION_REQUEST);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        assertNotNull(mes.ngKSI);
        assertEquals(mes.ngKSI.nasKeySetIdentifier, 0);
        assertEquals(mes.ngKSI.tsc, 0);

        assertNotNull(mes.abba);
        assertEquals(mes.abba.contents, new OctetString(new int[2]));

        assertInstance(mes.eapMessage.eap, EapAkaPrime.class);
        assertEquals(mes.eapMessage.eap.code, Eap.ECode.REQUEST);
        assertEquals(mes.eapMessage.eap.id, 1);
        assertEquals(mes.eapMessage.eap.EAPType, Eap.EEapType.EAP_AKA_PRIME);

        var akaPrime = (EapAkaPrime) mes.eapMessage.eap;
        assertEquals(akaPrime.subType, EapAkaPrime.ESubType.AKA_CHALLENGE);
        assertNotNull(akaPrime.attributes);
        assertEquals(akaPrime.attributes.size(), 5);
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_RAND), new OctetString("0000c1c855df1555ab38342f5e5242e286b2"));
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_AUTN), new OctetString("0000adca1f49a09c8000167c4316a3a016d1"));
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_KDF), new OctetString("0001"));
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_KDF_INPUT), new OctetString("002035473a6d6e633030312e6d63633030312e336770706e6574776f726b2e6f7267"));
        assertEquals(akaPrime.attributes.get(EapAkaPrime.EAttributeType.AT_MAC), new OctetString("00005addcf552b22f2909f7dde0050e22cbd"));
    }

    @Override
    public NasMessage getMessage() {
        var mes = new AuthenticationRequest();
        mes.messageType = EMessageType.AUTHENTICATION_REQUEST;
        mes.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        mes.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;

        mes.ngKSI = new IENasKeySetIdentifier();
        mes.ngKSI.nasKeySetIdentifier = new Bit3(0);
        mes.ngKSI.tsc = ETypeOfSecurityContext.fromValue(0);

        mes.abba = new IEAbba();
        mes.abba.contents = new OctetString("0000");

        var akaPrime = new EapAkaPrime(Eap.ECode.REQUEST, new Octet(1));
        mes.eapMessage = new IEEapMessage();
        mes.eapMessage.eap = akaPrime;

        akaPrime.subType = EapAkaPrime.ESubType.AKA_CHALLENGE;
        akaPrime.attributes = new LinkedHashMap<>();
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_RAND, new OctetString("0000c1c855df1555ab38342f5e5242e286b2"));
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_AUTN, new OctetString("0000adca1f49a09c8000167c4316a3a016d1"));
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_KDF, new OctetString("0001"));
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_KDF_INPUT, new OctetString("002035473a6d6e633030312e6d63633030312e336770706e6574776f726b2e6f7267"));
        akaPrime.attributes.put(EapAkaPrime.EAttributeType.AT_MAC, new OctetString("00005addcf552b22f2909f7dde0050e22cbd"));

        return mes;
    }
}
