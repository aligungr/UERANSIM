/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PH_UplinkCarrierMCG extends AsnSequence {
    public RRC_ph_Type1or3_1 ph_Type1or3; // mandatory

    public static class RRC_ph_Type1or3_1 extends AsnEnumerated {
        public static final RRC_ph_Type1or3_1 TYPE1 = new RRC_ph_Type1or3_1(0);
        public static final RRC_ph_Type1or3_1 TYPE3 = new RRC_ph_Type1or3_1(1);
    
        private RRC_ph_Type1or3_1(long value) {
            super(value);
        }
    }
}

