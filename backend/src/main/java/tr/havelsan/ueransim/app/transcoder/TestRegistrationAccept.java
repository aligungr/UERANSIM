package tr.havelsan.ueransim.app.transcoder;

import tr.havelsan.ueransim.app.TranscoderTesting;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.*;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.enums.*;
import tr.havelsan.ueransim.nas.impl.ies.*;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationAccept;
import tr.havelsan.ueransim.nas.impl.values.V5gTmsi;
import tr.havelsan.ueransim.nas.impl.values.VAmfSetId;
import tr.havelsan.ueransim.nas.impl.values.VSliceDifferentiator;
import tr.havelsan.ueransim.nas.impl.values.VSliceServiceType;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit10;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.Octet4;

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
        assertEquals(guti.mcc, EMccValue.unknownValue(1));
        assertEquals(guti.mnc, EMncValue.unknownValue(1));
        assertEquals(guti.amfRegionId, 42);
        assertNotNull(guti.amfSetId);
        assertEquals(guti.amfSetId.value, 342);
        assertEquals(guti.amfPointer, 42);
        assertNotNull(guti.tmsi);
        assertEquals(guti.tmsi.value, 0x00000001);

        assertNotNull(mes.registrationResult);
        assertEquals(mes.registrationResult.registrationResult, IE5gsRegistrationResult.E5gsRegistrationResult.THREEGPP_ACCESS);
        assertEquals(mes.registrationResult.smsOverNasAllowed, IE5gsRegistrationResult.ESmsOverNasTransportAllowed.ALLOWED);

        assertNotNull(mes.allowedNSSAI);
        assertNotNull(mes.allowedNSSAI.sNssais);
        assertEquals(mes.allowedNSSAI.sNssais.length, 1);
        assertNotNull(mes.allowedNSSAI.sNssais[0]);
        assertNotNull(mes.allowedNSSAI.sNssais[0].sst);
        assertNotNull(mes.allowedNSSAI.sNssais[0].sd);
        assertEquals(mes.allowedNSSAI.sNssais[0].sst.value, 1);
        assertEquals(mes.allowedNSSAI.sNssais[0].sd.value, 634799);

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
        guti.mcc = EMccValue.unknownValue(1);
        guti.mnc = EMncValue.unknownValue(1);
        guti.amfRegionId = new Octet(42);
        guti.amfSetId = new VAmfSetId();
        guti.amfSetId.value = new Bit10(342);
        guti.amfPointer = new Bit6(42);
        guti.tmsi = new V5gTmsi();
        guti.tmsi.value = new Octet4(0x00000001);

        message.registrationResult = new IE5gsRegistrationResult();
        message.registrationResult.registrationResult = IE5gsRegistrationResult.E5gsRegistrationResult.THREEGPP_ACCESS;
        message.registrationResult.smsOverNasAllowed = IE5gsRegistrationResult.ESmsOverNasTransportAllowed.ALLOWED;

        message.allowedNSSAI = new IENssai();
        message.allowedNSSAI.sNssais = new IESNssai[1];
        message.allowedNSSAI.sNssais[0] = new IESNssai();
        message.allowedNSSAI.sNssais[0].sst = new VSliceServiceType();
        message.allowedNSSAI.sNssais[0].sd = new VSliceDifferentiator();
        message.allowedNSSAI.sNssais[0].sst.value = new Octet(1);
        message.allowedNSSAI.sNssais[0].sd.value = new Octet3(634799);

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
