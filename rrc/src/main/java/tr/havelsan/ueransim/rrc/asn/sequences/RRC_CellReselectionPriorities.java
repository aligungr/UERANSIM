/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqPriorityListEUTRA;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqPriorityListNR;

public class RRC_CellReselectionPriorities extends RRC_Sequence {

    public RRC_FreqPriorityListEUTRA freqPriorityListEUTRA;
    public RRC_FreqPriorityListNR freqPriorityListNR;
    public RRC_Integer t320;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "freqPriorityListEUTRA","freqPriorityListNR","t320" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "freqPriorityListEUTRA","freqPriorityListNR","t320" };
    }

    @Override
    public String getAsnName() {
        return "CellReselectionPriorities";
    }

    @Override
    public String getXmlTagName() {
        return "CellReselectionPriorities";
    }

}
