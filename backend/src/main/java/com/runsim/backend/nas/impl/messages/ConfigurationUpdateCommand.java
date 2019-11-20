package com.runsim.backend.nas.impl.messages;

import com.runsim.backend.nas.core.IMessageBuilder;
import com.runsim.backend.nas.core.messages.PlainMmMessage;
import com.runsim.backend.nas.impl.ies.*;

public class ConfigurationUpdateCommand extends PlainMmMessage {
    public IEConfigurationUpdateIndication configurationUpdateIndication;
    public IE5gsMobileIdentity guti;
    public IE5gsTrackingAreaIdentityList taiList;
    public IENssai allowedNssai;
    public IEServiceAreaList serviceAreaList;
    public IENetworkName networkFullName;
    public IENetworkName networkShortName;
    public IETimeZone localTimeZone;
    public IETimeZoneAndTime universalTimeAndLocalTimeZone;
    public IEDaylightSavingTime networkDaylightSavingTime;
    public IELadnInformation ladnInformation;
    public IEMicoIndication micoIndication;
    public IENetworkSlicingIndication networkSlicingIndication;
    public IENssai configuredNssai;
    public IERejectedNssai rejectedNssai;
    public IEOperatorDefinedAccessCategoryDefinitions operatorDefinedAccessCategoryDefinitions;
    public IESmsIndication smsIndication;

    @Override
    public void build(IMessageBuilder builder) {
        super.build(builder);

        builder.optionalIE1(0xD, "configurationUpdateIndication");
        builder.optionalIE(0x77, "guti");
        builder.optionalIE(0x54, "taiList");
        builder.optionalIE(0x15, "allowedNssai");
        builder.optionalIE(0x27, "serviceAreaList");
        builder.optionalIE(0x43, "networkFullName");
        builder.optionalIE(0x45, "networkShortName");
        builder.optionalIE(0x46, "localTimeZone");
        builder.optionalIE(0x47, "universalTimeAndLocalTimeZone");
        builder.optionalIE(0x49, "networkDaylightSavingTime");
        builder.optionalIE(0x79, "ladnInformation");
        builder.optionalIE1(0xB, "micoIndication");
        builder.optionalIE1(0x9, "networkSlicingIndication");
        builder.optionalIE(0x31, "configuredNssai");
        builder.optionalIE(0x11, "rejectedNssai");
        builder.optionalIE(0x76, "operatorDefinedAccessCategoryDefinitions");
        builder.optionalIE1(0xF, "smsIndication");
    }
}
