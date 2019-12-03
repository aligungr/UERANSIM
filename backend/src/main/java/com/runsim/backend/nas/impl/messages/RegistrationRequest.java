package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.ies.*;

public class RegistrationRequest extends PlainMmMessage {
    public IE5gsRegistrationType registrationType;
    public IENasKeySetIdentifier nasKeySetIdentifier;
    public IE5gsMobileIdentity mobileIdentity;

    public IENasKeySetIdentifier nonCurrentNgKsi;
    public IEMicoIndication micoIndication;
    public IENetworkSlicingIndication networkSlicingIndication;

    public IEUeSecurityCapability ueSecurityCapability;
    public IE5gMmCapability mmCapability;
    public IENssai requestedNSSA;
    public IE5gsMobileIdentity additionalGuti;
    public IE5gsDrxParameters requestedDrxParameters;
    public IEUesUsageSetting uesUsageSetting;
    public IE5gsUpdateType updateType;
    public IEUeStatus ueStatus;
    public IEUplinkDataStatus uplinkDataStatus;
    public IEEpsNasMessageContainer epsNasMessageContainer;
    public IENasMessageContainer nasMessageContainer;
    public IEAllowedPduSessionStatus allowedPduSessionStatus;
    public IE5gsTrackingAreaIdentity lastVisitedRegisteredTai;
    public IES1UeNetworkCapability s1UeNetworkCapability;
    public IEPduSessionStatus pduSessionStatus;
    public IEPayloadContainer payloadContainer;
    public IELadnIndication ladnIndication;

    public RegistrationRequest() {
        super(EMessageType.REGISTRATION_REQUEST);
    }

    public RegistrationRequest(IE5gsRegistrationType registrationType, IENasKeySetIdentifier nasKeySetIdentifier, IE5gsMobileIdentity mobileIdentity, IENasKeySetIdentifier nonCurrentNgKsi, IEMicoIndication micoIndication, IENetworkSlicingIndication networkSlicingIndication, IEUeSecurityCapability ueSecurityCapability, IE5gMmCapability mmCapability, IENssai requestedNSSA, IE5gsMobileIdentity additionalGuti, IE5gsDrxParameters requestedDrxParameters, IEUesUsageSetting uesUsageSetting, IE5gsUpdateType updateType, IEUeStatus ueStatus, IEUplinkDataStatus uplinkDataStatus, IEEpsNasMessageContainer epsNasMessageContainer, IENasMessageContainer nasMessageContainer, IEAllowedPduSessionStatus allowedPduSessionStatus, IE5gsTrackingAreaIdentity lastVisitedRegisteredTai, IES1UeNetworkCapability s1UeNetworkCapability, IEPduSessionStatus pduSessionStatus, IEPayloadContainer payloadContainer, IELadnIndication ladnIndication) {
        this();
        this.registrationType = registrationType;
        this.nasKeySetIdentifier = nasKeySetIdentifier;
        this.mobileIdentity = mobileIdentity;
        this.nonCurrentNgKsi = nonCurrentNgKsi;
        this.micoIndication = micoIndication;
        this.networkSlicingIndication = networkSlicingIndication;
        this.ueSecurityCapability = ueSecurityCapability;
        this.mmCapability = mmCapability;
        this.requestedNSSA = requestedNSSA;
        this.additionalGuti = additionalGuti;
        this.requestedDrxParameters = requestedDrxParameters;
        this.uesUsageSetting = uesUsageSetting;
        this.updateType = updateType;
        this.ueStatus = ueStatus;
        this.uplinkDataStatus = uplinkDataStatus;
        this.epsNasMessageContainer = epsNasMessageContainer;
        this.nasMessageContainer = nasMessageContainer;
        this.allowedPduSessionStatus = allowedPduSessionStatus;
        this.lastVisitedRegisteredTai = lastVisitedRegisteredTai;
        this.s1UeNetworkCapability = s1UeNetworkCapability;
        this.pduSessionStatus = pduSessionStatus;
        this.payloadContainer = payloadContainer;
        this.ladnIndication = ladnIndication;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE1("nasKeySetIdentifier", "registrationType");
        builder.mandatoryIE("mobileIdentity");

        builder.optionalIE1(0xC, "nonCurrentNgKsi");
        builder.optionalIE1(0xB, "micoIndication");
        builder.optionalIE1(0x9, "networkSlicingIndication");

        builder.optionalIE(0x10, "mmCapability");
        builder.optionalIE(0x2E, "ueSecurityCapability");
        builder.optionalIE(0x2F, "requestedNSSA");
        builder.optionalIE(0x52, "lastVisitedRegisteredTai");
        builder.optionalIE(0x17, "s1UeNetworkCapability");
        builder.optionalIE(0x40, "uplinkDataStatus");
        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x2B, "ueStatus");
        builder.optionalIE(0x77, "additionalGuti");
        builder.optionalIE(0x25, "allowedPduSessionStatus");
        builder.optionalIE(0x18, "uesUsageSetting");
        builder.optionalIE(0x51, "requestedDrxParameters");
        builder.optionalIE(0x70, "epsNasMessageContainer");
        builder.optionalIE(0x7E, "ladnIndication");
        builder.optionalIE(0x7B, "payloadContainer");
        builder.optionalIE(0x53, "updateType");
        builder.optionalIE(0x71, "nasMessageContainer");
    }
}
