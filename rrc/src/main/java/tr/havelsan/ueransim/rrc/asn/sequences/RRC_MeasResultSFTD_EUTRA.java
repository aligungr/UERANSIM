/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_MeasResultSFTD_EUTRA extends RRC_Sequence {

    public RRC_EUTRA_PhysCellId eutra_PhysCellId;
    public RRC_Integer sfn_OffsetResult;
    public RRC_Integer frameBoundaryOffsetResult;
    public RRC_RSRP_Range rsrp_Result;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "eutra-PhysCellId","sfn-OffsetResult","frameBoundaryOffsetResult","rsrp-Result" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "eutra_PhysCellId","sfn_OffsetResult","frameBoundaryOffsetResult","rsrp_Result" };
    }

    @Override
    public String getAsnName() {
        return "MeasResultSFTD-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultSFTD-EUTRA";
    }

}
