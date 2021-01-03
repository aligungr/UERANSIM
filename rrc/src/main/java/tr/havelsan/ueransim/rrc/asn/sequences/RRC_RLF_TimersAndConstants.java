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

public class RRC_RLF_TimersAndConstants extends AsnSequence {
    public RRC_t310_1 t310; // mandatory
    public RRC_n310_2 n310; // mandatory
    public RRC_n311_2 n311; // mandatory
    public RRC_ext1_53 ext1; // optional

    public static class RRC_t310_1 extends AsnEnumerated {
        public static final RRC_t310_1 MS0 = new RRC_t310_1(0);
        public static final RRC_t310_1 MS50 = new RRC_t310_1(1);
        public static final RRC_t310_1 MS100 = new RRC_t310_1(2);
        public static final RRC_t310_1 MS200 = new RRC_t310_1(3);
        public static final RRC_t310_1 MS500 = new RRC_t310_1(4);
        public static final RRC_t310_1 MS1000 = new RRC_t310_1(5);
        public static final RRC_t310_1 MS2000 = new RRC_t310_1(6);
        public static final RRC_t310_1 MS4000 = new RRC_t310_1(7);
        public static final RRC_t310_1 MS6000 = new RRC_t310_1(8);
    
        private RRC_t310_1(long value) {
            super(value);
        }
    }

    public static class RRC_n310_2 extends AsnEnumerated {
        public static final RRC_n310_2 N1 = new RRC_n310_2(0);
        public static final RRC_n310_2 N2 = new RRC_n310_2(1);
        public static final RRC_n310_2 N3 = new RRC_n310_2(2);
        public static final RRC_n310_2 N4 = new RRC_n310_2(3);
        public static final RRC_n310_2 N6 = new RRC_n310_2(4);
        public static final RRC_n310_2 N8 = new RRC_n310_2(5);
        public static final RRC_n310_2 N10 = new RRC_n310_2(6);
        public static final RRC_n310_2 N20 = new RRC_n310_2(7);
    
        private RRC_n310_2(long value) {
            super(value);
        }
    }

    public static class RRC_n311_2 extends AsnEnumerated {
        public static final RRC_n311_2 N1 = new RRC_n311_2(0);
        public static final RRC_n311_2 N2 = new RRC_n311_2(1);
        public static final RRC_n311_2 N3 = new RRC_n311_2(2);
        public static final RRC_n311_2 N4 = new RRC_n311_2(3);
        public static final RRC_n311_2 N5 = new RRC_n311_2(4);
        public static final RRC_n311_2 N6 = new RRC_n311_2(5);
        public static final RRC_n311_2 N8 = new RRC_n311_2(6);
        public static final RRC_n311_2 N10 = new RRC_n311_2(7);
    
        private RRC_n311_2(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_53 extends AsnSequence {
        public RRC_t311_v1530 t311_v1530; // mandatory
    
        public static class RRC_t311_v1530 extends AsnEnumerated {
            public static final RRC_t311_v1530 MS1000 = new RRC_t311_v1530(0);
            public static final RRC_t311_v1530 MS3000 = new RRC_t311_v1530(1);
            public static final RRC_t311_v1530 MS5000 = new RRC_t311_v1530(2);
            public static final RRC_t311_v1530 MS10000 = new RRC_t311_v1530(3);
            public static final RRC_t311_v1530 MS15000 = new RRC_t311_v1530(4);
            public static final RRC_t311_v1530 MS20000 = new RRC_t311_v1530(5);
            public static final RRC_t311_v1530 MS30000 = new RRC_t311_v1530(6);
        
            private RRC_t311_v1530(long value) {
                super(value);
            }
        }
    }
}

