/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PDCP_ParametersMRDC extends RRC_Sequence {

    public RRC_Integer pdcp_DuplicationSplitSRB;
    public RRC_Integer pdcp_DuplicationSplitDRB;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdcp-DuplicationSplitSRB","pdcp-DuplicationSplitDRB" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdcp_DuplicationSplitSRB","pdcp_DuplicationSplitDRB" };
    }

    @Override
    public String getAsnName() {
        return "PDCP-ParametersMRDC";
    }

    @Override
    public String getXmlTagName() {
        return "PDCP-ParametersMRDC";
    }

}
