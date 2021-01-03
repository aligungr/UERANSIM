/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_SupportedBandwidth extends AsnChoice {
    public RRC_fr1_2 fr1;
    public RRC_fr2_1 fr2;

    public static class RRC_fr1_2 extends AsnEnumerated {
        public static final RRC_fr1_2 MHZ5 = new RRC_fr1_2(0);
        public static final RRC_fr1_2 MHZ10 = new RRC_fr1_2(1);
        public static final RRC_fr1_2 MHZ15 = new RRC_fr1_2(2);
        public static final RRC_fr1_2 MHZ20 = new RRC_fr1_2(3);
        public static final RRC_fr1_2 MHZ25 = new RRC_fr1_2(4);
        public static final RRC_fr1_2 MHZ30 = new RRC_fr1_2(5);
        public static final RRC_fr1_2 MHZ40 = new RRC_fr1_2(6);
        public static final RRC_fr1_2 MHZ50 = new RRC_fr1_2(7);
        public static final RRC_fr1_2 MHZ60 = new RRC_fr1_2(8);
        public static final RRC_fr1_2 MHZ80 = new RRC_fr1_2(9);
        public static final RRC_fr1_2 MHZ100 = new RRC_fr1_2(10);
    
        private RRC_fr1_2(long value) {
            super(value);
        }
    }

    public static class RRC_fr2_1 extends AsnEnumerated {
        public static final RRC_fr2_1 MHZ50 = new RRC_fr2_1(0);
        public static final RRC_fr2_1 MHZ100 = new RRC_fr2_1(1);
        public static final RRC_fr2_1 MHZ200 = new RRC_fr2_1(2);
        public static final RRC_fr2_1 MHZ400 = new RRC_fr2_1(3);
    
        private RRC_fr2_1(long value) {
            super(value);
        }
    }
}

