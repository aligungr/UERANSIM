/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqPriorityNR;

public class RRC_FreqPriorityListNR extends RRC_SequenceOf<RRC_FreqPriorityNR> {

    @Override
    public String getAsnName() {
        return "FreqPriorityListNR";
    }

    @Override
    public String getXmlTagName() {
        return "FreqPriorityListNR";
    }

    @Override
    public Class<RRC_FreqPriorityNR> getItemType() {
        return RRC_FreqPriorityNR.class;
    }

}
