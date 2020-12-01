/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_CellIndex;

public class RRC_EUTRA_CellIndexList extends RRC_SequenceOf<RRC_EUTRA_CellIndex> {

    @Override
    public String getAsnName() {
        return "EUTRA-CellIndexList";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-CellIndexList";
    }

    @Override
    public Class<RRC_EUTRA_CellIndex> getItemType() {
        return RRC_EUTRA_CellIndex.class;
    }

}
