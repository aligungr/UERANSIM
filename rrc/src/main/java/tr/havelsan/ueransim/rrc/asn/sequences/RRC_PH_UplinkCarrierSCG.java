/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PH_UplinkCarrierSCG extends AsnSequence {
    public RRC_ph_Type1or3_2 ph_Type1or3; // mandatory

    public static class RRC_ph_Type1or3_2 extends AsnEnumerated {
        public static final RRC_ph_Type1or3_2 TYPE1 = new RRC_ph_Type1or3_2(0);
        public static final RRC_ph_Type1or3_2 TYPE3 = new RRC_ph_Type1or3_2(1);
    
        private RRC_ph_Type1or3_2(long value) {
            super(value);
        }
    }
}

