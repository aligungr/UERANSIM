package com.runsim.backend.demo.decoder;

import com.runsim.backend.demo.DecoderTesting;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.*;
import com.runsim.backend.nas.impl.ies.IE5gGutiMobileIdentity;
import com.runsim.backend.nas.impl.messages.RegistrationAccept;

public class TestRegistrationAccept extends DecoderTesting.PduTest {

    @Override
    public String getPdu() {
        return "7e0042010977000bf20011002a55aa000000011505040109afaf50020000";
    }

    @Override
    public void compare(NasMessage message) {
        assertInstance(message, RegistrationAccept.class);
        var mes = (RegistrationAccept) message;
        assertEquals(mes.messageType, EMessageType.REGISTRATION_ACCEPT);
        assertEquals(mes.extendedProtocolDiscriminator, EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES);
        assertEquals(mes.securityHeaderType, ESecurityHeaderType.NOT_PROTECTED);

        assertInstance(mes.mobileIdentity, IE5gGutiMobileIdentity.class);
        var guti = (IE5gGutiMobileIdentity) mes.mobileIdentity;
        assertEquals(guti.mobileCountryCode, EMobileCountryCode.unknownValue(1));
        assertEquals(guti.mobileNetworkCode, EMobileNetworkCode3.unknownValue(guti.mobileCountryCode.value * 1000 + 1));
        assertEquals(guti.amfRegionId, 42);
        assertNotNull(guti.amfSetId);
        assertEquals(guti.amfSetId.value, 342);
        assertEquals(guti.amfPointer, 42);
        assertNotNull(guti.tmsi);
        assertEquals(guti.tmsi.value, 0x00000001);

        assertNotNull(mes.registrationResult);
        assertEquals(mes.registrationResult.registrationResult, E5gsRegistrationResult.THREEGPP_ACCESS);
        assertEquals(mes.registrationResult.smsOverNasAllowed, ESmsOverNasTransportAllowed.ALLOWED);

        assertNotNull(mes.allowedNSSA);
        assertNotNull(mes.allowedNSSA.sNssas);
        assertEquals(mes.allowedNSSA.sNssas.length, 1);
        assertNotNull(mes.allowedNSSA.sNssas[0]);
        assertNotNull(mes.allowedNSSA.sNssas[0].sst);
        assertNotNull(mes.allowedNSSA.sNssas[0].sd);
        assertEquals(mes.allowedNSSA.sNssas[0].sst.value, 1);
        assertEquals(mes.allowedNSSA.sNssas[0].sd.value, 634799);

        assertNotNull(mes.pduSessionStatus);
        assertEquals(mes.pduSessionStatus.psi01, 0);
        assertEquals(mes.pduSessionStatus.psi02, 0);
        assertEquals(mes.pduSessionStatus.psi03, 0);
        assertEquals(mes.pduSessionStatus.psi04, 0);
        assertEquals(mes.pduSessionStatus.psi05, 0);
        assertEquals(mes.pduSessionStatus.psi06, 0);
        assertEquals(mes.pduSessionStatus.psi07, 0);
        assertEquals(mes.pduSessionStatus.psi08, 0);
        assertEquals(mes.pduSessionStatus.psi09, 0);
        assertEquals(mes.pduSessionStatus.psi10, 0);
        assertEquals(mes.pduSessionStatus.psi11, 0);
        assertEquals(mes.pduSessionStatus.psi12, 0);
        assertEquals(mes.pduSessionStatus.psi13, 0);
        assertEquals(mes.pduSessionStatus.psi14, 0);
        assertEquals(mes.pduSessionStatus.psi15, 0);
    }
}
