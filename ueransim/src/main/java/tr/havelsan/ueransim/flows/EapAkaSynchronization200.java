package tr.havelsan.ueransim.flows;

import tr.havelsan.ueransim.BaseFlow;
import tr.havelsan.ueransim.Message;
import tr.havelsan.ueransim.app.ue.UeUtils;
import tr.havelsan.ueransim.app.ue.UserLocationInformationNr;
import tr.havelsan.ueransim.nas.impl.enums.EMccValue;
import tr.havelsan.ueransim.nas.impl.enums.EMncValue;
import tr.havelsan.ueransim.nas.impl.ies.IE5gsRegistrationType;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.ies.IEImsiMobileIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IENasKeySetIdentifier;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationResponse;
import tr.havelsan.ueransim.nas.impl.messages.RegistrationRequest;
import tr.havelsan.ueransim.nas.impl.values.VHomeNetworkPki;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.nas.impl.values.VTrackingAreaIdentity;
import tr.havelsan.ueransim.ngap.ngap_ies.RRCEstablishmentCause;
import tr.havelsan.ueransim.utils.bits.Bit3;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class EapAkaSynchronization200 extends BaseFlow {

    public State main(Message message) {
        return sendRegistrationRequest(message);
    }

    private State sendRegistrationRequest(Message message) {
        var nasMessage = new RegistrationRequest();
        nasMessage.mobileIdentity = new IEImsiMobileIdentity(EMccValue.fromValue(1), EMncValue.fromValue(1), "0000", IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEME, new VHomeNetworkPki(0), "000000028");
        nasMessage.registrationType = new IE5gsRegistrationType(IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING, IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);
        nasMessage.nasKeySetIdentifier = new IENasKeySetIdentifier(IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, new Bit3(3));

        var ngapPdu = UeUtils.createInitialUeMessage(nasMessage, 150, RRCEstablishmentCause.ASN_mo_Data, makeUserLocationInformation());
        sendPDU(ngapPdu);
        return this::authenticationResponse;
    }

    private UserLocationInformationNr makeUserLocationInformation() {
        return new UserLocationInformationNr(new UserLocationInformationNr.NrCgi(new VPlmn(1, 1), new OctetString("01b2c3d4e0")),
                new VTrackingAreaIdentity(new VPlmn(1, 1), new Octet3(0x00, 0x00, 0x75)), new OctetString("5b5fa680"));
    }

    private State authenticationResponse(Message message) {
        var nasMessage = new AuthenticationResponse();
        nasMessage.eapMessage = new IEEapMessage(UeUtils.decodeEapFromBase64("MDI1YjAwMmMzMjAxMDAwMDAzMDMwMDQwZGE2Y2Q2M2MxMDUxMzQ0OTBiMDUwMDAwOGY5M2VhZTc3NDQyZjlhNGNhNTQxNzE5NGFiZDVkOWMxODAxMDAwMQ=="));

        var ngapPdu = UeUtils.createUplinkMessage(nasMessage, 150, 1, makeUserLocationInformation());
        sendPDU(ngapPdu);

        return this::sinkState;
    }

}
