/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PUCCH_format4 extends AsnSequence {
    public AsnInteger nrofSymbols; // mandatory, VALUE(4..14)
    public RRC_occ_Length occ_Length; // mandatory
    public RRC_occ_Index occ_Index; // mandatory
    public AsnInteger startingSymbolIndex; // mandatory, VALUE(0..10)

    public static class RRC_occ_Length extends AsnEnumerated {
        public static final RRC_occ_Length N2 = new RRC_occ_Length(0);
        public static final RRC_occ_Length N4 = new RRC_occ_Length(1);
    
        private RRC_occ_Length(long value) {
            super(value);
        }
    }

    public static class RRC_occ_Index extends AsnEnumerated {
        public static final RRC_occ_Index N0 = new RRC_occ_Index(0);
        public static final RRC_occ_Index N1 = new RRC_occ_Index(1);
        public static final RRC_occ_Index N2 = new RRC_occ_Index(2);
        public static final RRC_occ_Index N3 = new RRC_occ_Index(3);
    
        private RRC_occ_Index(long value) {
            super(value);
        }
    }
}

