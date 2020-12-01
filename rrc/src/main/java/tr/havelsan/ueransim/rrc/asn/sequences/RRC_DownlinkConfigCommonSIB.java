/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DownlinkConfigCommonSIB extends RRC_Sequence {

    public RRC_FrequencyInfoDL_SIB frequencyInfoDL;
    public RRC_BWP_DownlinkCommon initialDownlinkBWP;
    public RRC_BCCH_Config bcch_Config;
    public RRC_PCCH_Config pcch_Config;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyInfoDL","initialDownlinkBWP","bcch-Config","pcch-Config" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyInfoDL","initialDownlinkBWP","bcch_Config","pcch_Config" };
    }

    @Override
    public String getAsnName() {
        return "DownlinkConfigCommonSIB";
    }

    @Override
    public String getXmlTagName() {
        return "DownlinkConfigCommonSIB";
    }

}
