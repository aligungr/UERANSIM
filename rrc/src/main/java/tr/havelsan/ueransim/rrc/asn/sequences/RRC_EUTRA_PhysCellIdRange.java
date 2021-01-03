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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_EUTRA_PhysCellIdRange extends AsnSequence {
    public RRC_EUTRA_PhysCellId start; // mandatory
    public RRC_range_1 range; // optional

    public static class RRC_range_1 extends AsnEnumerated {
        public static final RRC_range_1 N4 = new RRC_range_1(0);
        public static final RRC_range_1 N8 = new RRC_range_1(1);
        public static final RRC_range_1 N12 = new RRC_range_1(2);
        public static final RRC_range_1 N16 = new RRC_range_1(3);
        public static final RRC_range_1 N24 = new RRC_range_1(4);
        public static final RRC_range_1 N32 = new RRC_range_1(5);
        public static final RRC_range_1 N48 = new RRC_range_1(6);
        public static final RRC_range_1 N64 = new RRC_range_1(7);
        public static final RRC_range_1 N84 = new RRC_range_1(8);
        public static final RRC_range_1 N96 = new RRC_range_1(9);
        public static final RRC_range_1 N128 = new RRC_range_1(10);
        public static final RRC_range_1 N168 = new RRC_range_1(11);
        public static final RRC_range_1 N252 = new RRC_range_1(12);
        public static final RRC_range_1 N504 = new RRC_range_1(13);
        public static final RRC_range_1 SPARE2 = new RRC_range_1(14);
        public static final RRC_range_1 SPARE1 = new RRC_range_1(15);
    
        private RRC_range_1(long value) {
            super(value);
        }
    }
}

