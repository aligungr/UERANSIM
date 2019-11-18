package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.ies.*;

public class RegistrationAccept extends PlainNasMessage {

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

    @Override
    public void transcode(ITranscodeBuilder builder) {
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
        //builder.optionalIE(0x79, null); // todo: not implemented yet
        builder.optionalIE(0x27, "serviceAreaList");
        builder.optionalIE(0x5E, "t3512Value");
        builder.optionalIE(0x5D, "non3gppDeRegistrationTimerValue");
        builder.optionalIE(0x16, "t3502Value");
        builder.optionalIE(0x34, "emergencyNumberList");
        //builder.optionalIE(0x7A, null); // todo: not implemented yet
        //builder.optionalIE(0x73, null); // todo: not implemented yet
        builder.optionalIE(0x78, "eapMessage");
        builder.optionalIE(0x76, "operatorDefinedAccessCategoryDefinitions");
        builder.optionalIE(0x51, "negotiatedDrxParameters");
    }
}
