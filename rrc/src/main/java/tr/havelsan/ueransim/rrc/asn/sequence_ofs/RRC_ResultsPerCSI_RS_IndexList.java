/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ResultsPerCSI_RS_Index;

public class RRC_ResultsPerCSI_RS_IndexList extends RRC_SequenceOf<RRC_ResultsPerCSI_RS_Index> {

    @Override
    public String getAsnName() {
        return "ResultsPerCSI-RS-IndexList";
    }

    @Override
    public String getXmlTagName() {
        return "ResultsPerCSI-RS-IndexList";
    }

    @Override
    public Class<RRC_ResultsPerCSI_RS_Index> getItemType() {
        return RRC_ResultsPerCSI_RS_Index.class;
    }

}
