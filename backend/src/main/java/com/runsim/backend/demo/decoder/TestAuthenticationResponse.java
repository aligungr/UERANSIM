package com.runsim.backend.demo.decoder;

import com.runsim.backend.demo.DecoderTesting;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.eap.*;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.messages.AuthenticationResponse;
import com.runsim.backend.utils.octets.OctetString;

public class TestAuthenticationResponse extends DecoderTesting.PduTest {

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
        assertNotNull(mes.eap);

        assertInstance(mes.eap, AkaPrime.class);
        assertEquals(mes.eap.code, ECode.RESPONSE);
        assertEquals(mes.eap.id, 1);
        assertEquals(mes.eap.length, 48);
        assertEquals(mes.eap.EAPType, EEapType.EAP_AKA_PRIME);

        var akaPrime = (AkaPrime) mes.eap;
        assertEquals(akaPrime.subType, EAkaSubType.AKA_CHALLENGE);
        assertEquals(akaPrime.attributes.size(), 2);
        assertEquals(akaPrime.attributes.get(EAkaAttributeType.AT_RES), new OctetString("000864955b0fe729127b0000000000000000"));
        assertEquals(akaPrime.attributes.get(EAkaAttributeType.AT_MAC), new OctetString("000069f5f2af9798323126ef3cf8896a8c4b"));
    }
}
