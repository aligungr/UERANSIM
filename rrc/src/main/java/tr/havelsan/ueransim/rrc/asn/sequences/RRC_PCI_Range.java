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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_PCI_Range extends AsnSequence {
    public RRC_PhysCellId start; // mandatory
    public RRC_range_2 range; // optional

    public static class RRC_range_2 extends AsnEnumerated {
        public static final RRC_range_2 N4 = new RRC_range_2(0);
        public static final RRC_range_2 N8 = new RRC_range_2(1);
        public static final RRC_range_2 N12 = new RRC_range_2(2);
        public static final RRC_range_2 N16 = new RRC_range_2(3);
        public static final RRC_range_2 N24 = new RRC_range_2(4);
        public static final RRC_range_2 N32 = new RRC_range_2(5);
        public static final RRC_range_2 N48 = new RRC_range_2(6);
        public static final RRC_range_2 N64 = new RRC_range_2(7);
        public static final RRC_range_2 N84 = new RRC_range_2(8);
        public static final RRC_range_2 N96 = new RRC_range_2(9);
        public static final RRC_range_2 N128 = new RRC_range_2(10);
        public static final RRC_range_2 N168 = new RRC_range_2(11);
        public static final RRC_range_2 N252 = new RRC_range_2(12);
        public static final RRC_range_2 N504 = new RRC_range_2(13);
        public static final RRC_range_2 N1008 = new RRC_range_2(14);
        public static final RRC_range_2 SPARE1 = new RRC_range_2(15);
    
        private RRC_range_2(long value) {
            super(value);
        }
    }
}

