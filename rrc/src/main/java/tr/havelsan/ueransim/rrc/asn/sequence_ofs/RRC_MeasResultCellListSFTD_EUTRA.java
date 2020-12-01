/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasResultSFTD_EUTRA;

public class RRC_MeasResultCellListSFTD_EUTRA extends RRC_SequenceOf<RRC_MeasResultSFTD_EUTRA> {

    @Override
    public String getAsnName() {
        return "MeasResultCellListSFTD-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "MeasResultCellListSFTD-EUTRA";
    }

    @Override
    public Class<RRC_MeasResultSFTD_EUTRA> getItemType() {
        return RRC_MeasResultSFTD_EUTRA.class;
    }

}
