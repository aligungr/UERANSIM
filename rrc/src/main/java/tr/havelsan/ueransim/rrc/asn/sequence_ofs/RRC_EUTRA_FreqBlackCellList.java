/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_EUTRA_PhysCellIdRange;

public class RRC_EUTRA_FreqBlackCellList extends RRC_SequenceOf<RRC_EUTRA_PhysCellIdRange> {

    @Override
    public String getAsnName() {
        return "EUTRA-FreqBlackCellList";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-FreqBlackCellList";
    }

    @Override
    public Class<RRC_EUTRA_PhysCellIdRange> getItemType() {
        return RRC_EUTRA_PhysCellIdRange.class;
    }

}
