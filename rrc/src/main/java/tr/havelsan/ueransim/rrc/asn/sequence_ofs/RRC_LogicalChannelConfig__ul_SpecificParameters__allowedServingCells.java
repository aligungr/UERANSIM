/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_LogicalChannelConfig__ul_SpecificParameters__allowedServingCells extends RRC_SequenceOf<RRC_ServCellIndex> {

    @Override
    public Class<RRC_ServCellIndex> getItemType() {
        return RRC_ServCellIndex.class;
    }

}
