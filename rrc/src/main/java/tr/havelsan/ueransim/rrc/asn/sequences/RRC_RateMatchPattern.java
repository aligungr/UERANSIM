/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RateMatchPattern__patternType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;

public class RRC_RateMatchPattern extends RRC_Sequence {

    public RRC_RateMatchPatternId rateMatchPatternId;
    public RRC_RateMatchPattern__patternType patternType;
    public RRC_SubcarrierSpacing subcarrierSpacing;
    public RRC_Integer dummy;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "rateMatchPatternId","patternType","subcarrierSpacing","dummy" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "rateMatchPatternId","patternType","subcarrierSpacing","dummy" };
    }

    @Override
    public String getAsnName() {
        return "RateMatchPattern";
    }

    @Override
    public String getXmlTagName() {
        return "RateMatchPattern";
    }

}
