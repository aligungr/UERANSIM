/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SlotFormatCombination;

public class RRC_SlotFormatCombinationsPerCell__slotFormatCombinations extends RRC_SequenceOf<RRC_SlotFormatCombination> {

    @Override
    public Class<RRC_SlotFormatCombination> getItemType() {
        return RRC_SlotFormatCombination.class;
    }

}
