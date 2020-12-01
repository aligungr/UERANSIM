/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MCC_MNC_Digit;

public class RRC_MCC extends RRC_SequenceOf<RRC_MCC_MNC_Digit> {

    @Override
    public String getAsnName() {
        return "MCC";
    }

    @Override
    public String getXmlTagName() {
        return "MCC";
    }

    @Override
    public Class<RRC_MCC_MNC_Digit> getItemType() {
        return RRC_MCC_MNC_Digit.class;
    }

}
