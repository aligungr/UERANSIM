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

    public ConfigurationUpdateCommand() {
        super(EMessageType.CONFIGURATION_UPDATE_COMMAND);
    }

    public ConfigurationUpdateCommand(IEConfigurationUpdateIndication configurationUpdateIndication, IE5gsMobileIdentity guti, IE5gsTrackingAreaIdentityList taiList, IENssai allowedNssai, IEServiceAreaList serviceAreaList, IENetworkName networkFullName, IENetworkName networkShortName, IETimeZone localTimeZone, IETimeZoneAndTime universalTimeAndLocalTimeZone, IEDaylightSavingTime networkDaylightSavingTime, IELadnInformation ladnInformation, IEMicoIndication micoIndication, IENetworkSlicingIndication networkSlicingIndication, IENssai configuredNssai, IERejectedNssai rejectedNssai, IEOperatorDefinedAccessCategoryDefinitions operatorDefinedAccessCategoryDefinitions, IESmsIndication smsIndication) {
        this();
        this.configurationUpdateIndication = configurationUpdateIndication;
        this.guti = guti;
        this.taiList = taiList;
        this.allowedNssai = allowedNssai;
        this.serviceAreaList = serviceAreaList;
        this.networkFullName = networkFullName;
        this.networkShortName = networkShortName;
        this.localTimeZone = localTimeZone;
        this.universalTimeAndLocalTimeZone = universalTimeAndLocalTimeZone;
        this.networkDaylightSavingTime = networkDaylightSavingTime;
        this.ladnInformation = ladnInformation;
        this.micoIndication = micoIndication;
        this.networkSlicingIndication = networkSlicingIndication;
        this.configuredNssai = configuredNssai;
        this.rejectedNssai = rejectedNssai;
        this.operatorDefinedAccessCategoryDefinitions = operatorDefinedAccessCategoryDefinitions;
        this.smsIndication = smsIndication;
    }

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
