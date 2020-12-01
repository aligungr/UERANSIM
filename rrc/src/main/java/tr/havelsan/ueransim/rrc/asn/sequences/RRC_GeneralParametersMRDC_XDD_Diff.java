/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_GeneralParametersMRDC_XDD_Diff extends RRC_Sequence {

    public RRC_Integer splitSRB_WithOneUL_Path;
    public RRC_Integer splitDRB_withUL_Both_MCG_SCG;
    public RRC_Integer srb3;
    public RRC_Integer v2x_EUTRA;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "splitSRB-WithOneUL-Path","splitDRB-withUL-Both-MCG-SCG","srb3","v2x-EUTRA" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "splitSRB_WithOneUL_Path","splitDRB_withUL_Both_MCG_SCG","srb3","v2x_EUTRA" };
    }

    @Override
    public String getAsnName() {
        return "GeneralParametersMRDC-XDD-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "GeneralParametersMRDC-XDD-Diff";
    }

}
