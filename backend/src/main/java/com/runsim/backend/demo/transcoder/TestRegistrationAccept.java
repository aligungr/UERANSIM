package com.runsim.backend.demo.transcoder;

import com.runsim.backend.demo.TranscoderTesting;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.impl.enums.*;
import com.runsim.backend.nas.impl.ies.*;
import com.runsim.backend.nas.impl.messages.RegistrationAccept;
import com.runsim.backend.nas.impl.values.V5gTmsi;
import com.runsim.backend.nas.impl.values.VAmfSetId;
import com.runsim.backend.nas.impl.values.VSliceDifferentiator;
import com.runsim.backend.nas.impl.values.VSliceServiceType;
import com.runsim.backend.utils.bits.Bit;
import com.runsim.backend.utils.bits.Bit6;
import com.runsim.backend.utils.bits.BitN;
import com.runsim.backend.utils.octets.Octet;
import com.runsim.backend.utils.octets.Octet3;
import com.runsim.backend.utils.octets.Octet4;

public class TestRegistrationAccept extends TranscoderTesting.PduTest {

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

        assertNotNull(mes.allowedNSSAI);
        assertNotNull(mes.allowedNSSAI.sNssas);
        assertEquals(mes.allowedNSSAI.sNssas.length, 1);
        assertNotNull(mes.allowedNSSAI.sNssas[0]);
        assertNotNull(mes.allowedNSSAI.sNssas[0].sst);
        assertNotNull(mes.allowedNSSAI.sNssas[0].sd);
        assertEquals(mes.allowedNSSAI.sNssas[0].sst.value, 1);
        assertEquals(mes.allowedNSSAI.sNssas[0].sd.value, 634799);

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

    @Override
    public NasMessage getMessage() {
        var message = new RegistrationAccept();
        message.messageType = EMessageType.REGISTRATION_ACCEPT;
        message.extendedProtocolDiscriminator = EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES;
        message.securityHeaderType = ESecurityHeaderType.NOT_PROTECTED;

        var guti = new IE5gGutiMobileIdentity();
        message.mobileIdentity = guti;
        guti.mobileCountryCode = EMobileCountryCode.unknownValue(1);
        guti.mobileNetworkCode = EMobileNetworkCode3.unknownValue(guti.mobileCountryCode.value * 1000 + 1);
        guti.amfRegionId = new Octet(42);
        guti.amfSetId = new VAmfSetId();
        guti.amfSetId.value = new BitN(342, 10);
        guti.amfPointer = new Bit6(42);
        guti.tmsi = new V5gTmsi();
        guti.tmsi.value = new Octet4(0x00000001);

        message.registrationResult = new IE5gsRegistrationResult();
        message.registrationResult.registrationResult = E5gsRegistrationResult.THREEGPP_ACCESS;
        message.registrationResult.smsOverNasAllowed = ESmsOverNasTransportAllowed.ALLOWED;

        message.allowedNSSAI = new IENssai();
        message.allowedNSSAI.sNssas = new IESNssa[1];
        message.allowedNSSAI.sNssas[0] = new IESNssa();
        message.allowedNSSAI.sNssas[0].sst = new VSliceServiceType();
        message.allowedNSSAI.sNssas[0].sd = new VSliceDifferentiator();
        message.allowedNSSAI.sNssas[0].sst.value = new Octet(1);
        message.allowedNSSAI.sNssas[0].sd.value = new Octet3(634799);

        message.pduSessionStatus = new IEPduSessionStatus();
        message.pduSessionStatus.psi01 = new Bit(0);
        message.pduSessionStatus.psi02 = new Bit(0);
        message.pduSessionStatus.psi03 = new Bit(0);
        message.pduSessionStatus.psi04 = new Bit(0);
        message.pduSessionStatus.psi05 = new Bit(0);
        message.pduSessionStatus.psi06 = new Bit(0);
        message.pduSessionStatus.psi07 = new Bit(0);
        message.pduSessionStatus.psi08 = new Bit(0);
        message.pduSessionStatus.psi09 = new Bit(0);
        message.pduSessionStatus.psi10 = new Bit(0);
        message.pduSessionStatus.psi11 = new Bit(0);
        message.pduSessionStatus.psi12 = new Bit(0);
        message.pduSessionStatus.psi13 = new Bit(0);
        message.pduSessionStatus.psi14 = new Bit(0);
        message.pduSessionStatus.psi15 = new Bit(0);

        return message;
    }
}
