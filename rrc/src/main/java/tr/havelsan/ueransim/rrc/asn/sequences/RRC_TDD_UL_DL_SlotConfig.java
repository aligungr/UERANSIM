/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_TDD_UL_DL_SlotConfig__symbols;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TDD_UL_DL_SlotIndex;

public class RRC_TDD_UL_DL_SlotConfig extends RRC_Sequence {

    public RRC_TDD_UL_DL_SlotIndex slotIndex;
    public RRC_TDD_UL_DL_SlotConfig__symbols symbols;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "slotIndex","symbols" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "slotIndex","symbols" };
    }

    @Override
    public String getAsnName() {
        return "TDD-UL-DL-SlotConfig";
    }

    @Override
    public String getXmlTagName() {
        return "TDD-UL-DL-SlotConfig";
    }

}
