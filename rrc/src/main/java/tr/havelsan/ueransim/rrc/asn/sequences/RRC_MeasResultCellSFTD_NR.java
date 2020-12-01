/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_MeasResultCellSFTD_NR extends RRC_Sequence {

    public RRC_PhysCellId physCellId;
    public RRC_Integer sfn_OffsetResult;
    public RRC_Integer frameBoundaryOffsetResult;
    public RRC_RSRP_Range rsrp_Result;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "physCellId","sfn-OffsetResult","frameBoundaryOffsetResult","rsrp-Result" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "physCellId","sfn_OffsetResult","frameBoundaryOffsetResult","rsrp_Result" };
    }

    @Override
    public String getAsnName() {
        return "MeasResultCellSFTD-NR";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultCellSFTD-NR";
    }

}
