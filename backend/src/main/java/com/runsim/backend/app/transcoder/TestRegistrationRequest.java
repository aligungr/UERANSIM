package com.runsim.backend.app.transcoder;

import com.runsim.backend.app.TranscoderTesting;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.*;
import com.runsim.backend.nas.impl.ies.IE5gsRegistrationType;
import com.runsim.backend.nas.impl.ies.IEImsiMobileIdentity;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;
import com.runsim.backend.nas.impl.ies.IEUeSecurityCapability;
import com.runsim.backend.nas.impl.messages.RegistrationRequest;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.Bit3;
import com.runsim.backend.utils.octets.Octet;

public class TestRegistrationRequest extends TranscoderTesting.PduTest {

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

        assertEquals(mes.registrationType.followOnRequestPending, IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING);
        assertEquals(mes.registrationType.registrationType, IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);

        assertEquals(mes.nasKeySetIdentifier.tsc, IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT);
        assertEquals(mes.nasKeySetIdentifier.nasKeySetIdentifier, new Bit3(7));

        var imsi = (IEImsiMobileIdentity) mes.mobileIdentity;
        assertInstance(mes.mobileIdentity, IEImsiMobileIdentity.class);
        assertEquals(imsi.mcc, EMobileCountryCode.unknownValue(1));
        assertEquals(imsi.mnc, EMobileNetworkCode3.unknownValue(imsi.mcc.intValue() * 1000 + 1));
        assertEquals(imsi.routingIndicator, "0000");
        assertEquals(imsi.protectionSchemaId, IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEMA);
        assertEquals(imsi.schemaOutput, "000000001");
        assertNotNull(imsi.homeNetworkPublicKeyIdentifier);
        assertEquals(imsi.homeNetworkPublicKeyIdentifier.value, 0);

        var capability = (IEUeSecurityCapability) mes.ueSecurityCapability;
        assertNotNull(capability);
        assertEquals(capability.supported_5G_EA0, 1);
        assertEquals(capability.supported_5G_IA0, 1);
        assertEquals(capability.supported_EEA0, 1);
        assertEquals(capability.supported_EIA0, 1);

        assertEquals(capability.supported_128_5G_EA1, 0);
        assertEquals(capability.supported_128_5G_EA2, 0);
        assertEquals(capability.supported_128_5G_EA3, 0);
        assertEquals(capability.supported_5G_EA4, 0);
        assertEquals(capability.supported_5G_EA5, 0);
        assertEquals(capability.supported_5G_EA6, 0);
        assertEquals(capability.supported_5G_EA7, 0);
        assertEquals(capability.supported_128_5G_IA1, 0);
        assertEquals(capability.supported_128_5G_IA2, 0);
        assertEquals(capability.supported_128_5G_IA3, 0);
        assertEquals(capability.supported_5G_IA4, 0);
        assertEquals(capability.supported_5G_IA5, 0);
        assertEquals(capability.supported_5G_IA6, 0);
        assertEquals(capability.supported_5G_IA7, 0);
        assertEquals(capability.supported_128_EEA1, 0);
        assertEquals(capability.supported_128_EEA2, 0);
        assertEquals(capability.supported_128_EEA3, 0);
        assertEquals(capability.supported_EEA4, 0);
        assertEquals(capability.supported_EEA5, 0);
        assertEquals(capability.supported_EEA6, 0);
        assertEquals(capability.supported_EEA7, 0);
        assertEquals(capability.supported_128_EIA1, 0);
        assertEquals(capability.supported_128_EIA2, 0);
        assertEquals(capability.supported_128_EIA3, 0);
        assertEquals(capability.supported_EIA4, 0);
        assertEquals(capability.supported_EIA5, 0);
        assertEquals(capability.supported_EIA6, 0);
        assertEquals(capability.supported_EIA7, 0);
    }

    @Override
    public NasMessage getMessage() {
        var nasMessage = new RegistrationRequest();
        nasMessage.messageType = EMessageType.REGISTRATION_REQUEST;
        nasMessage.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        nasMessage.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;
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

        return nasMessage;
    }
}
