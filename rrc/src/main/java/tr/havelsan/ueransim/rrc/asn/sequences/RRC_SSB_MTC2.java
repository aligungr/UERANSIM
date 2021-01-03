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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_SSB_MTC2 extends AsnSequence {
    public RRC_pci_List pci_List; // optional, SIZE(1..64)
    public RRC_periodicity_2 periodicity; // mandatory

    // SIZE(1..64)
    public static class RRC_pci_List extends AsnSequenceOf<RRC_PhysCellId> {
    }

    public static class RRC_periodicity_2 extends AsnEnumerated {
        public static final RRC_periodicity_2 SF5 = new RRC_periodicity_2(0);
        public static final RRC_periodicity_2 SF10 = new RRC_periodicity_2(1);
        public static final RRC_periodicity_2 SF20 = new RRC_periodicity_2(2);
        public static final RRC_periodicity_2 SF40 = new RRC_periodicity_2(3);
        public static final RRC_periodicity_2 SF80 = new RRC_periodicity_2(4);
        public static final RRC_periodicity_2 SPARE3 = new RRC_periodicity_2(5);
        public static final RRC_periodicity_2 SPARE2 = new RRC_periodicity_2(6);
        public static final RRC_periodicity_2 SPARE1 = new RRC_periodicity_2(7);
    
        private RRC_periodicity_2(long value) {
            super(value);
        }
    }
}

