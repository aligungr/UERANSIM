/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MCC_MNC_Digit;

public class RRC_MNC extends RRC_SequenceOf<RRC_MCC_MNC_Digit> {

    @Override
    public String getAsnName() {
        return "MNC";
    }

    @Override
    public String getXmlTagName() {
        return "MNC";
    }

    @Override
    public Class<RRC_MCC_MNC_Digit> getItemType() {
        return RRC_MCC_MNC_Digit.class;
    }

}
