/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TDD_UL_DL_SlotIndex;

public class RRC_TDD_UL_DL_SlotConfig extends AsnSequence {
    public RRC_TDD_UL_DL_SlotIndex slotIndex; // mandatory
    public RRC_symbols symbols; // mandatory

    public static class RRC_symbols extends AsnChoice {
        public AsnNull allDownlink;
        public AsnNull allUplink;
        public RRC_explicit explicit;
    
        public static class RRC_explicit extends AsnSequence {
            public AsnInteger nrofDownlinkSymbols; // optional, VALUE(1..13)
            public AsnInteger nrofUplinkSymbols; // optional, VALUE(1..13)
        }
    }
}

