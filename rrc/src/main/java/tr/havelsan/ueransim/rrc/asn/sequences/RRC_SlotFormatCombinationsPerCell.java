/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_SlotFormatCombinationsPerCell extends AsnSequence {
    public RRC_ServCellIndex servingCellId; // mandatory
    public RRC_SubcarrierSpacing subcarrierSpacing; // mandatory
    public RRC_SubcarrierSpacing subcarrierSpacing2; // optional
    public RRC_slotFormatCombinations slotFormatCombinations; // optional, SIZE(1..512)
    public AsnInteger positionInDCI; // optional, VALUE(0..127)

    // SIZE(1..512)
    public static class RRC_slotFormatCombinations extends AsnSequenceOf<RRC_SlotFormatCombination> {
    }
}

