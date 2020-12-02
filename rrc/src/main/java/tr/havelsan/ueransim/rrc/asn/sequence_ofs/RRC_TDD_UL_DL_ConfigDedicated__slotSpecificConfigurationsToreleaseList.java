/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TDD_UL_DL_SlotIndex;

public class RRC_TDD_UL_DL_ConfigDedicated__slotSpecificConfigurationsToreleaseList extends RRC_SequenceOf<RRC_TDD_UL_DL_SlotIndex> {

    @Override
    public Class<RRC_TDD_UL_DL_SlotIndex> getItemType() {
        return RRC_TDD_UL_DL_SlotIndex.class;
    }

}
