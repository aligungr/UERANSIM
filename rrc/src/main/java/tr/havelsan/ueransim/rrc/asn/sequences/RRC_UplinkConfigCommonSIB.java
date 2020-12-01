/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeAlignmentTimer;

public class RRC_UplinkConfigCommonSIB extends RRC_Sequence {

    public RRC_FrequencyInfoUL_SIB frequencyInfoUL;
    public RRC_BWP_UplinkCommon initialUplinkBWP;
    public RRC_TimeAlignmentTimer timeAlignmentTimerCommon;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyInfoUL","initialUplinkBWP","timeAlignmentTimerCommon" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyInfoUL","initialUplinkBWP","timeAlignmentTimerCommon" };
    }

    @Override
    public String getAsnName() {
        return "UplinkConfigCommonSIB";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkConfigCommonSIB";
    }

}
