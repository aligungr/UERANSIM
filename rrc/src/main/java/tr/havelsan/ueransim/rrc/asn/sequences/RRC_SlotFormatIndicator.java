/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_SlotFormatIndicator extends AsnSequence {
    public RRC_RNTI_Value sfi_RNTI; // mandatory
    public AsnInteger dci_PayloadSize; // mandatory, VALUE(1..128)
    public RRC_slotFormatCombToAddModList slotFormatCombToAddModList; // optional, SIZE(1..16)
    public RRC_slotFormatCombToReleaseList slotFormatCombToReleaseList; // optional, SIZE(1..16)

    // SIZE(1..16)
    public static class RRC_slotFormatCombToAddModList extends AsnSequenceOf<RRC_SlotFormatCombinationsPerCell> {
    }

    // SIZE(1..16)
    public static class RRC_slotFormatCombToReleaseList extends AsnSequenceOf<RRC_ServCellIndex> {
    }
}

