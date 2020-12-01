/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeAlignmentTimer;

public class RRC_UplinkConfigCommon extends RRC_Sequence {

    public RRC_FrequencyInfoUL frequencyInfoUL;
    public RRC_BWP_UplinkCommon initialUplinkBWP;
    public RRC_TimeAlignmentTimer dummy;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyInfoUL","initialUplinkBWP","dummy" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyInfoUL","initialUplinkBWP","dummy" };
    }

    @Override
    public String getAsnName() {
        return "UplinkConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "UplinkConfigCommon";
    }

}
