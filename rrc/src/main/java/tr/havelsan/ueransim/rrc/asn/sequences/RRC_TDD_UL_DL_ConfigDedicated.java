/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TDD_UL_DL_SlotIndex;

public class RRC_TDD_UL_DL_ConfigDedicated extends AsnSequence {
    public RRC_slotSpecificConfigurationsToAddModList slotSpecificConfigurationsToAddModList; // optional, SIZE(1..320)
    public RRC_slotSpecificConfigurationsToreleaseList slotSpecificConfigurationsToreleaseList; // optional, SIZE(1..320)

    // SIZE(1..320)
    public static class RRC_slotSpecificConfigurationsToAddModList extends AsnSequenceOf<RRC_TDD_UL_DL_SlotConfig> {
    }

    // SIZE(1..320)
    public static class RRC_slotSpecificConfigurationsToreleaseList extends AsnSequenceOf<RRC_TDD_UL_DL_SlotIndex> {
    }
}

