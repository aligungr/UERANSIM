/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RateMatchPatternGroup__Member;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_RateMatchPatternGroup extends RRC_SequenceOf<RRC_RateMatchPatternGroup__Member> {

    @Override
    public String getAsnName() {
        return "RateMatchPatternGroup";
    }

    @Override
    public String getXmlTagName() {
        return "RateMatchPatternGroup";
    }

    @Override
    public Class<RRC_RateMatchPatternGroup__Member> getItemType() {
        return RRC_RateMatchPatternGroup__Member.class;
    }

}
