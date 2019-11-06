package com.runsim.backend.demo.decoder;

import com.runsim.backend.demo.DecoderTesting;
import com.runsim.backend.protocols.bits.Bit3;
import com.runsim.backend.protocols.nas.impl.enums.*;
import com.runsim.backend.protocols.nas.impl.ie.IEImsiMobileIdentity;
import com.runsim.backend.protocols.nas.impl.messages.RegistrationRequest;
import com.runsim.backend.protocols.nas.impl.values.VUeSecurityCapability;
import com.runsim.backend.protocols.nas.messages.NasMessage;

public class TestRegistrationRequest extends DecoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e004171000d010011000000000000000000f12e0480808080";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, RegistrationRequest.class);
        var mes = (RegistrationRequest) message;
        assertEquals(mes.messageType, EMessageType.REGISTRATION_REQUEST);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        assertNotNull(mes.mobileIdentity);
        assertNotNull(mes.registrationType);
        assertNotNull(mes.ueSecurityCapability);
        assertNotNull(mes.nasKeySetIdentifier);

        assertEquals(mes.registrationType.followOnRequestPending, EFollowOnRequest.NO_FOR_PENDING);
        assertEquals(mes.registrationType.registrationType, ERegistrationType.INITIAL_REGISTRATION);

        assertEquals(mes.nasKeySetIdentifier.tsc, ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT);
        assertEquals(mes.nasKeySetIdentifier.nasKeySetIdentifier, new Bit3(7));

        var imsi = (IEImsiMobileIdentity) mes.mobileIdentity;
        assertInstance(mes.mobileIdentity, IEImsiMobileIdentity.class);
        assertEquals(imsi.mobileCountryCode, EMobileCountryCode.unknownValue(1));
        assertEquals(imsi.mobileNetworkCode, EMobileNetworkCode3.unknownValue(imsi.mobileCountryCode.value * 1000 + 1));
        assertEquals(imsi.routingIndicator, "0000");
        assertEquals(imsi.protectionSchemaId, EProtectionSchemeIdentifier.NULL_SCHEMA);
        assertEquals(imsi.schemaOutput, "000000001");
        assertNotNull(imsi.homeNetworkPublicKeyIdentifier);
        assertEquals(imsi.homeNetworkPublicKeyIdentifier.value, 0);

        var capability = (VUeSecurityCapability) mes.ueSecurityCapability;
        assertNotNull(capability);
        assertEquals(capability.SUPPORTED_5G_EA0, 1);
        assertEquals(capability.SUPPORTED_5G_IA0, 1);
        assertEquals(capability.SUPPORTED_EEA0, 1);
        assertEquals(capability.SUPPORTED_EIA0, 1);

        assertEquals(capability.SUPPORTED_128_5G_EA1, 0);
        assertEquals(capability.SUPPORTED_128_5G_EA2, 0);
        assertEquals(capability.SUPPORTED_128_5G_EA3, 0);
        assertEquals(capability.SUPPORTED_5G_EA4, 0);
        assertEquals(capability.SUPPORTED_5G_EA5, 0);
        assertEquals(capability.SUPPORTED_5G_EA6, 0);
        assertEquals(capability.SUPPORTED_5G_EA7, 0);
        assertEquals(capability.SUPPORTED_128_5G_IA1, 0);
        assertEquals(capability.SUPPORTED_128_5G_IA2, 0);
        assertEquals(capability.SUPPORTED_128_5G_IA3, 0);
        assertEquals(capability.SUPPORTED_5G_IA4, 0);
        assertEquals(capability.SUPPORTED_5G_IA5, 0);
        assertEquals(capability.SUPPORTED_5G_IA6, 0);
        assertEquals(capability.SUPPORTED_5G_IA7, 0);
        assertEquals(capability.SUPPORTED_128_EEA1, 0);
        assertEquals(capability.SUPPORTED_128_EEA2, 0);
        assertEquals(capability.SUPPORTED_128_EEA3, 0);
        assertEquals(capability.SUPPORTED_EEA4, 0);
        assertEquals(capability.SUPPORTED_EEA5, 0);
        assertEquals(capability.SUPPORTED_EEA6, 0);
        assertEquals(capability.SUPPORTED_EEA7, 0);
        assertEquals(capability.SUPPORTED_128_EIA1, 0);
        assertEquals(capability.SUPPORTED_128_EIA2, 0);
        assertEquals(capability.SUPPORTED_128_EIA3, 0);
        assertEquals(capability.SUPPORTED_EIA4, 0);
        assertEquals(capability.SUPPORTED_EIA5, 0);
        assertEquals(capability.SUPPORTED_EIA6, 0);
        assertEquals(capability.SUPPORTED_EIA7, 0);
    }
}
