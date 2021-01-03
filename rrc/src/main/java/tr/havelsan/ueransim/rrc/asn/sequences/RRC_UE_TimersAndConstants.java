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

public class RRC_UE_TimersAndConstants extends AsnSequence {
    public RRC_t300 t300; // mandatory
    public RRC_t301 t301; // mandatory
    public RRC_t310_2 t310; // mandatory
    public RRC_n310_1 n310; // mandatory
    public RRC_t311 t311; // mandatory
    public RRC_n311_1 n311; // mandatory
    public RRC_t319 t319; // mandatory

    public static class RRC_t301 extends AsnEnumerated {
        public static final RRC_t301 MS100 = new RRC_t301(0);
        public static final RRC_t301 MS200 = new RRC_t301(1);
        public static final RRC_t301 MS300 = new RRC_t301(2);
        public static final RRC_t301 MS400 = new RRC_t301(3);
        public static final RRC_t301 MS600 = new RRC_t301(4);
        public static final RRC_t301 MS1000 = new RRC_t301(5);
        public static final RRC_t301 MS1500 = new RRC_t301(6);
        public static final RRC_t301 MS2000 = new RRC_t301(7);
    
        private RRC_t301(long value) {
            super(value);
        }
    }

    public static class RRC_n311_1 extends AsnEnumerated {
        public static final RRC_n311_1 N1 = new RRC_n311_1(0);
        public static final RRC_n311_1 N2 = new RRC_n311_1(1);
        public static final RRC_n311_1 N3 = new RRC_n311_1(2);
        public static final RRC_n311_1 N4 = new RRC_n311_1(3);
        public static final RRC_n311_1 N5 = new RRC_n311_1(4);
        public static final RRC_n311_1 N6 = new RRC_n311_1(5);
        public static final RRC_n311_1 N8 = new RRC_n311_1(6);
        public static final RRC_n311_1 N10 = new RRC_n311_1(7);
    
        private RRC_n311_1(long value) {
            super(value);
        }
    }

    public static class RRC_n310_1 extends AsnEnumerated {
        public static final RRC_n310_1 N1 = new RRC_n310_1(0);
        public static final RRC_n310_1 N2 = new RRC_n310_1(1);
        public static final RRC_n310_1 N3 = new RRC_n310_1(2);
        public static final RRC_n310_1 N4 = new RRC_n310_1(3);
        public static final RRC_n310_1 N6 = new RRC_n310_1(4);
        public static final RRC_n310_1 N8 = new RRC_n310_1(5);
        public static final RRC_n310_1 N10 = new RRC_n310_1(6);
        public static final RRC_n310_1 N20 = new RRC_n310_1(7);
    
        private RRC_n310_1(long value) {
            super(value);
        }
    }

    public static class RRC_t310_2 extends AsnEnumerated {
        public static final RRC_t310_2 MS0 = new RRC_t310_2(0);
        public static final RRC_t310_2 MS50 = new RRC_t310_2(1);
        public static final RRC_t310_2 MS100 = new RRC_t310_2(2);
        public static final RRC_t310_2 MS200 = new RRC_t310_2(3);
        public static final RRC_t310_2 MS500 = new RRC_t310_2(4);
        public static final RRC_t310_2 MS1000 = new RRC_t310_2(5);
        public static final RRC_t310_2 MS2000 = new RRC_t310_2(6);
    
        private RRC_t310_2(long value) {
            super(value);
        }
    }

    public static class RRC_t311 extends AsnEnumerated {
        public static final RRC_t311 MS1000 = new RRC_t311(0);
        public static final RRC_t311 MS3000 = new RRC_t311(1);
        public static final RRC_t311 MS5000 = new RRC_t311(2);
        public static final RRC_t311 MS10000 = new RRC_t311(3);
        public static final RRC_t311 MS15000 = new RRC_t311(4);
        public static final RRC_t311 MS20000 = new RRC_t311(5);
        public static final RRC_t311 MS30000 = new RRC_t311(6);
    
        private RRC_t311(long value) {
            super(value);
        }
    }

    public static class RRC_t319 extends AsnEnumerated {
        public static final RRC_t319 MS100 = new RRC_t319(0);
        public static final RRC_t319 MS200 = new RRC_t319(1);
        public static final RRC_t319 MS300 = new RRC_t319(2);
        public static final RRC_t319 MS400 = new RRC_t319(3);
        public static final RRC_t319 MS600 = new RRC_t319(4);
        public static final RRC_t319 MS1000 = new RRC_t319(5);
        public static final RRC_t319 MS1500 = new RRC_t319(6);
        public static final RRC_t319 MS2000 = new RRC_t319(7);
    
        private RRC_t319(long value) {
            super(value);
        }
    }

    public static class RRC_t300 extends AsnEnumerated {
        public static final RRC_t300 MS100 = new RRC_t300(0);
        public static final RRC_t300 MS200 = new RRC_t300(1);
        public static final RRC_t300 MS300 = new RRC_t300(2);
        public static final RRC_t300 MS400 = new RRC_t300(3);
        public static final RRC_t300 MS600 = new RRC_t300(4);
        public static final RRC_t300 MS1000 = new RRC_t300(5);
        public static final RRC_t300 MS1500 = new RRC_t300(6);
        public static final RRC_t300 MS2000 = new RRC_t300(7);
    
        private RRC_t300(long value) {
            super(value);
        }
    }
}

