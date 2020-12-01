/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ResultsPerCSI_RS_IndexList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ResultsPerSSB_IndexList;

public class RRC_MeasResultNR__measResult__rsIndexResults extends RRC_Sequence {

    public RRC_ResultsPerSSB_IndexList resultsSSB_Indexes;
    public RRC_ResultsPerCSI_RS_IndexList resultsCSI_RS_Indexes;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "resultsSSB-Indexes","resultsCSI-RS-Indexes" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "resultsSSB_Indexes","resultsCSI_RS_Indexes" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
