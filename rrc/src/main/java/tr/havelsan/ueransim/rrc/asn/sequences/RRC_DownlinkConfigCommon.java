/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_DownlinkConfigCommon extends RRC_Sequence {

    public RRC_FrequencyInfoDL frequencyInfoDL;
    public RRC_BWP_DownlinkCommon initialDownlinkBWP;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyInfoDL","initialDownlinkBWP" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyInfoDL","initialDownlinkBWP" };
    }

    @Override
    public String getAsnName() {
        return "DownlinkConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "DownlinkConfigCommon";
    }

}
