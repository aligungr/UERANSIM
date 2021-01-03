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

public class RRC_SRS_SwitchingTimeEUTRA extends AsnSequence {
    public RRC_switchingTimeDL_2 switchingTimeDL; // optional
    public RRC_switchingTimeUL_1 switchingTimeUL; // optional

    public static class RRC_switchingTimeUL_1 extends AsnEnumerated {
        public static final RRC_switchingTimeUL_1 N0 = new RRC_switchingTimeUL_1(0);
        public static final RRC_switchingTimeUL_1 N0DOT5 = new RRC_switchingTimeUL_1(1);
        public static final RRC_switchingTimeUL_1 N1 = new RRC_switchingTimeUL_1(2);
        public static final RRC_switchingTimeUL_1 N1DOT5 = new RRC_switchingTimeUL_1(3);
        public static final RRC_switchingTimeUL_1 N2 = new RRC_switchingTimeUL_1(4);
        public static final RRC_switchingTimeUL_1 N2DOT5 = new RRC_switchingTimeUL_1(5);
        public static final RRC_switchingTimeUL_1 N3 = new RRC_switchingTimeUL_1(6);
        public static final RRC_switchingTimeUL_1 N3DOT5 = new RRC_switchingTimeUL_1(7);
        public static final RRC_switchingTimeUL_1 N4 = new RRC_switchingTimeUL_1(8);
        public static final RRC_switchingTimeUL_1 N4DOT5 = new RRC_switchingTimeUL_1(9);
        public static final RRC_switchingTimeUL_1 N5 = new RRC_switchingTimeUL_1(10);
        public static final RRC_switchingTimeUL_1 N5DOT5 = new RRC_switchingTimeUL_1(11);
        public static final RRC_switchingTimeUL_1 N6 = new RRC_switchingTimeUL_1(12);
        public static final RRC_switchingTimeUL_1 N6DOT5 = new RRC_switchingTimeUL_1(13);
        public static final RRC_switchingTimeUL_1 N7 = new RRC_switchingTimeUL_1(14);
    
        private RRC_switchingTimeUL_1(long value) {
            super(value);
        }
    }

    public static class RRC_switchingTimeDL_2 extends AsnEnumerated {
        public static final RRC_switchingTimeDL_2 N0 = new RRC_switchingTimeDL_2(0);
        public static final RRC_switchingTimeDL_2 N0DOT5 = new RRC_switchingTimeDL_2(1);
        public static final RRC_switchingTimeDL_2 N1 = new RRC_switchingTimeDL_2(2);
        public static final RRC_switchingTimeDL_2 N1DOT5 = new RRC_switchingTimeDL_2(3);
        public static final RRC_switchingTimeDL_2 N2 = new RRC_switchingTimeDL_2(4);
        public static final RRC_switchingTimeDL_2 N2DOT5 = new RRC_switchingTimeDL_2(5);
        public static final RRC_switchingTimeDL_2 N3 = new RRC_switchingTimeDL_2(6);
        public static final RRC_switchingTimeDL_2 N3DOT5 = new RRC_switchingTimeDL_2(7);
        public static final RRC_switchingTimeDL_2 N4 = new RRC_switchingTimeDL_2(8);
        public static final RRC_switchingTimeDL_2 N4DOT5 = new RRC_switchingTimeDL_2(9);
        public static final RRC_switchingTimeDL_2 N5 = new RRC_switchingTimeDL_2(10);
        public static final RRC_switchingTimeDL_2 N5DOT5 = new RRC_switchingTimeDL_2(11);
        public static final RRC_switchingTimeDL_2 N6 = new RRC_switchingTimeDL_2(12);
        public static final RRC_switchingTimeDL_2 N6DOT5 = new RRC_switchingTimeDL_2(13);
        public static final RRC_switchingTimeDL_2 N7 = new RRC_switchingTimeDL_2(14);
    
        private RRC_switchingTimeDL_2(long value) {
            super(value);
        }
    }
}

