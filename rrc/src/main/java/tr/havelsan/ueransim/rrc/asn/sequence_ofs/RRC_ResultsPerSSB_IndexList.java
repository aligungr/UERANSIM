/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ResultsPerSSB_Index;

public class RRC_ResultsPerSSB_IndexList extends RRC_SequenceOf<RRC_ResultsPerSSB_Index> {

    @Override
    public String getAsnName() {
        return "ResultsPerSSB-IndexList";
    }

    @Override
    public String getXmlTagName() {
        return "ResultsPerSSB-IndexList";
    }

    @Override
    public Class<RRC_ResultsPerSSB_Index> getItemType() {
        return RRC_ResultsPerSSB_Index.class;
    }

}
