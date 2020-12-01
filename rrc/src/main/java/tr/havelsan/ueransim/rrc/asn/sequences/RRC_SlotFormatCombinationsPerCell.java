/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_SlotFormatCombinationsPerCell__slotFormatCombinations;

public class RRC_SlotFormatCombinationsPerCell extends RRC_Sequence {

    public RRC_ServCellIndex servingCellId;
    public RRC_SubcarrierSpacing subcarrierSpacing;
    public RRC_SubcarrierSpacing subcarrierSpacing2;
    public RRC_SlotFormatCombinationsPerCell__slotFormatCombinations slotFormatCombinations;
    public RRC_Integer positionInDCI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "servingCellId","subcarrierSpacing","subcarrierSpacing2","slotFormatCombinations","positionInDCI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "servingCellId","subcarrierSpacing","subcarrierSpacing2","slotFormatCombinations","positionInDCI" };
    }

    @Override
    public String getAsnName() {
        return "SlotFormatCombinationsPerCell";
    }

    @Override
    public String getXmlTagName() {
        return "SlotFormatCombinationsPerCell";
    }

}
