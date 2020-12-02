/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_RateMatchPattern__patternType__bitmaps__symbolsInResourceBlock extends RRC_Choice {

    public RRC_BitString oneSlot;
    public RRC_BitString twoSlots;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "oneSlot","twoSlots" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "oneSlot","twoSlots" };
    }

}
