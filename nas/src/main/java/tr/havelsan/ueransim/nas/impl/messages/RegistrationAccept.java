/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.messages;

import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.ies.*;

public class RegistrationAccept extends PlainMmMessage {
    public IE5gsRegistrationResult registrationResult;

    public IENetworkSlicingIndication networkSlicingIndication;
    public IENssaiInclusionMode nssaiInclusionMode;
    public IEMicoIndication micoIndication;

    public IE5gsMobileIdentity mobileIdentity;
    public IENssai allowedNSSAI;
    public IEPduSessionStatus pduSessionStatus;
    public IEEapMessage eapMessage;
    public IEPlmnList equivalentPLMNs;
    public IERejectedNssai rejectedNSSAI;
    public IENssai configuredNSSAI;
    public IE5gsNetworkFeatureSupport networkFeatureSupport;
    public IEPduSessionReactivationResult pduSessionReactivationResult;
    public IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause;
    public IEGprsTimer3 t3512Value;
    public IEGprsTimer2 t3502Value;
    public IEGprsTimer2 non3gppDeRegistrationTimerValue;
    public IE5gsDrxParameters negotiatedDrxParameters;
    public IE5gsTrackingAreaIdentityList taiList;
    public IEServiceAreaList serviceAreaList;
    public IEEmergencyNumberList emergencyNumberList;
    public IEOperatorDefinedAccessCategoryDefinitions operatorDefinedAccessCategoryDefinitions;
    public IELadnInformation ladnInformation;
    public IESorTransparentContainer sorTransparentContainer;
    public IEExtendedEmergencyNumberList extendedEmergencyNumberList;

    public RegistrationAccept() {
        super(EMessageType.REGISTRATION_ACCEPT);
    }

    public RegistrationAccept(IE5gsRegistrationResult registrationResult, IENetworkSlicingIndication networkSlicingIndication, IENssaiInclusionMode nssaiInclusionMode, IEMicoIndication micoIndication, IE5gsMobileIdentity mobileIdentity, IENssai allowedNSSAI, IEPduSessionStatus pduSessionStatus, IEEapMessage eapMessage, IEPlmnList equivalentPLMNs, IERejectedNssai rejectedNSSAI, IENssai configuredNSSAI, IE5gsNetworkFeatureSupport networkFeatureSupport, IEPduSessionReactivationResult pduSessionReactivationResult, IEPduSessionReactivationResultErrorCause pduSessionReactivationResultErrorCause, IEGprsTimer3 t3512Value, IEGprsTimer2 t3502Value, IEGprsTimer2 non3gppDeRegistrationTimerValue, IE5gsDrxParameters negotiatedDrxParameters, IE5gsTrackingAreaIdentityList taiList, IEServiceAreaList serviceAreaList, IEEmergencyNumberList emergencyNumberList, IEOperatorDefinedAccessCategoryDefinitions operatorDefinedAccessCategoryDefinitions, IELadnInformation ladnInformation, IESorTransparentContainer sorTransparentContainer, IEExtendedEmergencyNumberList extendedEmergencyNumberList) {
        this();
        this.registrationResult = registrationResult;
        this.networkSlicingIndication = networkSlicingIndication;
        this.nssaiInclusionMode = nssaiInclusionMode;
        this.micoIndication = micoIndication;
        this.mobileIdentity = mobileIdentity;
        this.allowedNSSAI = allowedNSSAI;
        this.pduSessionStatus = pduSessionStatus;
        this.eapMessage = eapMessage;
        this.equivalentPLMNs = equivalentPLMNs;
        this.rejectedNSSAI = rejectedNSSAI;
        this.configuredNSSAI = configuredNSSAI;
        this.networkFeatureSupport = networkFeatureSupport;
        this.pduSessionReactivationResult = pduSessionReactivationResult;
        this.pduSessionReactivationResultErrorCause = pduSessionReactivationResultErrorCause;
        this.t3512Value = t3512Value;
        this.t3502Value = t3502Value;
        this.non3gppDeRegistrationTimerValue = non3gppDeRegistrationTimerValue;
        this.negotiatedDrxParameters = negotiatedDrxParameters;
        this.taiList = taiList;
        this.serviceAreaList = serviceAreaList;
        this.emergencyNumberList = emergencyNumberList;
        this.operatorDefinedAccessCategoryDefinitions = operatorDefinedAccessCategoryDefinitions;
        this.ladnInformation = ladnInformation;
        this.sorTransparentContainer = sorTransparentContainer;
        this.extendedEmergencyNumberList = extendedEmergencyNumberList;
    }

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.mandatoryIE("registrationResult");

        builder.optionalIE1(0x9, "networkSlicingIndication");
        builder.optionalIE1(0xA, "nssaiInclusionMode");
        builder.optionalIE1(0xB, "micoIndication");

        builder.optionalIE(0x77, "mobileIdentity");
        builder.optionalIE(0x4A, "equivalentPLMNs");
        builder.optionalIE(0x54, "taiList");
        builder.optionalIE(0x15, "allowedNSSAI");
        builder.optionalIE(0x11, "rejectedNSSAI");
        builder.optionalIE(0x31, "configuredNSSAI");
        builder.optionalIE(0x21, "networkFeatureSupport");
        builder.optionalIE(0x50, "pduSessionStatus");
        builder.optionalIE(0x26, "pduSessionReactivationResult");
        builder.optionalIE(0x72, "pduSessionReactivationResultErrorCause");
        builder.optionalIE(0x79, "ladnInformation");
        builder.optionalIE(0x27, "serviceAreaList");
        builder.optionalIE(0x5E, "t3512Value");
        builder.optionalIE(0x5D, "non3gppDeRegistrationTimerValue");
        builder.optionalIE(0x16, "t3502Value");
        builder.optionalIE(0x34, "emergencyNumberList");
        builder.optionalIE(0x7A, "extendedEmergencyNumberList");
        builder.optionalIE(0x73, "sorTransparentContainer");
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x76, "operatorDefinedAccessCategoryDefinitions");
        builder.optionalIE(0x51, "negotiatedDrxParameters");
    }
}
