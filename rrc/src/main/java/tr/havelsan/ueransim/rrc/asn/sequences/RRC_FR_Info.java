/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_FR_Info extends AsnSequence {
    public RRC_ServCellIndex servCellIndex; // mandatory
    public RRC_fr_Type fr_Type; // mandatory

    public static class RRC_fr_Type extends AsnEnumerated {
        public static final RRC_fr_Type FR1 = new RRC_fr_Type(0);
        public static final RRC_fr_Type FR2 = new RRC_fr_Type(1);
    
        private RRC_fr_Type(long value) {
            super(value);
        }
    }
}

