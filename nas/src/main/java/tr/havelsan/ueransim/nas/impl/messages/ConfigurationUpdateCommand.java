/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
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
