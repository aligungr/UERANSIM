/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasResultCellSFTD_NR;

public class RRC_MeasResultCellListSFTD_NR extends RRC_SequenceOf<RRC_MeasResultCellSFTD_NR> {

    @Override
    public String getAsnName() {
        return "MeasResultCellListSFTD-NR";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultCellListSFTD-NR";
    }

    @Override
    public Class<RRC_MeasResultCellSFTD_NR> getItemType() {
        return RRC_MeasResultCellSFTD_NR.class;
    }

}
