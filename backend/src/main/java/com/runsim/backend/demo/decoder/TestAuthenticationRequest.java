package com.runsim.backend.demo.decoder;

import com.runsim.backend.demo.DecoderTesting;
import com.runsim.backend.protocols.eap.*;
import com.runsim.backend.protocols.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.protocols.nas.impl.enums.EMessageType;
import com.runsim.backend.protocols.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.protocols.nas.impl.messages.AuthenticationRequest;
import com.runsim.backend.protocols.nas.messages.NasMessage;
import com.runsim.backend.protocols.octets.OctetString;

public class TestAuthenticationRequest extends DecoderTesting.PduTest {

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

        assertInstance(mes.eap, AKAPrime.class);
        assertEquals(mes.eap.code, Code.REQUEST);
        assertEquals(mes.eap.id, 1);
        assertEquals(mes.eap.length, 108);
        assertEquals(mes.eap.EAPType, EAPType.EAP_AKA_PRIME);

        var akaPrime = (AKAPrime) mes.eap;
        assertEquals(akaPrime.subType, AKASubType.AKA_CHALLENGE);
        assertEquals(akaPrime.attributes.size(), 5);
        assertEquals(akaPrime.attributes.get(AKAAttributeType.AT_RAND), new OctetString("0000c1c855df1555ab38342f5e5242e286b2"));
        assertEquals(akaPrime.attributes.get(AKAAttributeType.AT_AUTN), new OctetString("0000adca1f49a09c8000167c4316a3a016d1"));
        assertEquals(akaPrime.attributes.get(AKAAttributeType.AT_KDF), new OctetString("0001"));
        assertEquals(akaPrime.attributes.get(AKAAttributeType.AT_KDF_INPUT), new OctetString("002035473a6d6e633030312e6d63633030312e336770706e6574776f726b2e6f7267"));
        assertEquals(akaPrime.attributes.get(AKAAttributeType.AT_MAC), new OctetString("00005addcf552b22f2909f7dde0050e22cbd"));
    }
}
