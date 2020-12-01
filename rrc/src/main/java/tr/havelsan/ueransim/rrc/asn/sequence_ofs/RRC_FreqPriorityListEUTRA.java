/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FreqPriorityEUTRA;

public class RRC_FreqPriorityListEUTRA extends RRC_SequenceOf<RRC_FreqPriorityEUTRA> {

    @Override
    public String getAsnName() {
        return "FreqPriorityListEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "FreqPriorityListEUTRA";
    }

    @Override
    public Class<RRC_FreqPriorityEUTRA> getItemType() {
        return RRC_FreqPriorityEUTRA.class;
    }

}
