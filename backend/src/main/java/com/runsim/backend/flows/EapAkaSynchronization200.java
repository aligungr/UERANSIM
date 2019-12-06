package com.runsim.backend.flows;

import com.runsim.backend.BaseFlow;
import com.runsim.backend.Message;
import com.runsim.backend.app.ue.UeUtils;
import com.runsim.backend.nas.impl.enums.EMobileCountryCode;
import com.runsim.backend.nas.impl.enums.EMobileNetworkCode3;
import com.runsim.backend.nas.impl.ies.IE5gsRegistrationType;
import com.runsim.backend.nas.impl.ies.IEEapMessage;
import com.runsim.backend.nas.impl.ies.IEImsiMobileIdentity;
import com.runsim.backend.nas.impl.ies.IENasKeySetIdentifier;
import com.runsim.backend.nas.impl.messages.AuthenticationResponse;
import com.runsim.backend.nas.impl.messages.RegistrationRequest;
import com.runsim.backend.nas.impl.values.VHomeNetworkPki;
import com.runsim.backend.ngap.ngap_ies.RRCEstablishmentCause;
import com.runsim.backend.utils.bits.Bit3;

public class EapAkaSynchronization200 extends BaseFlow {

    public State main(Message message) {
        return sendRegistrationRequest(message);
    }

    private State sendRegistrationRequest(Message message) {
        var nasMessage = new RegistrationRequest();
        nasMessage.mobileIdentity = new IEImsiMobileIdentity(EMobileCountryCode.fromValue(1), EMobileNetworkCode3.fromValue(1), "0000", IEImsiMobileIdentity.EProtectionSchemeIdentifier.NULL_SCHEMA, new VHomeNetworkPki(0), "000000028");
        nasMessage.registrationType = new IE5gsRegistrationType(IE5gsRegistrationType.EFollowOnRequest.NO_FOR_PENDING, IE5gsRegistrationType.ERegistrationType.INITIAL_REGISTRATION);
        nasMessage.nasKeySetIdentifier = new IENasKeySetIdentifier(IENasKeySetIdentifier.ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT, new Bit3(3));

        var ngapPdu = UeUtils.createInitialUeMessage(nasMessage, 150, RRCEstablishmentCause.ASN_mo_Data);
        sendPDU(ngapPdu);
        return this::authenticationResponse;
    }

    private State authenticationResponse(Message message) {
        var nasMessage = new AuthenticationResponse();
        nasMessage.eapMessage = new IEEapMessage(UeUtils.decodeEapFromBase64("MDI1YjAwMmMzMjAxMDAwMDAzMDMwMDQwZGE2Y2Q2M2MxMDUxMzQ0OTBiMDUwMDAwOGY5M2VhZTc3NDQyZjlhNGNhNTQxNzE5NGFiZDVkOWMxODAxMDAwMQ=="));

        var ngapPdu = UeUtils.createUplinkMessage(nasMessage, 150, 1);
        sendPDU(ngapPdu);

        return this::sinkState;
    }

}
