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

public class RRC_SRS_SwitchingTimeNR extends AsnSequence {
    public RRC_switchingTimeDL_1 switchingTimeDL; // optional
    public RRC_switchingTimeUL_2 switchingTimeUL; // optional

    public static class RRC_switchingTimeUL_2 extends AsnEnumerated {
        public static final RRC_switchingTimeUL_2 N0US = new RRC_switchingTimeUL_2(0);
        public static final RRC_switchingTimeUL_2 N30US = new RRC_switchingTimeUL_2(1);
        public static final RRC_switchingTimeUL_2 N100US = new RRC_switchingTimeUL_2(2);
        public static final RRC_switchingTimeUL_2 N140US = new RRC_switchingTimeUL_2(3);
        public static final RRC_switchingTimeUL_2 N200US = new RRC_switchingTimeUL_2(4);
        public static final RRC_switchingTimeUL_2 N300US = new RRC_switchingTimeUL_2(5);
        public static final RRC_switchingTimeUL_2 N500US = new RRC_switchingTimeUL_2(6);
        public static final RRC_switchingTimeUL_2 N900US = new RRC_switchingTimeUL_2(7);
    
        private RRC_switchingTimeUL_2(long value) {
            super(value);
        }
    }

    public static class RRC_switchingTimeDL_1 extends AsnEnumerated {
        public static final RRC_switchingTimeDL_1 N0US = new RRC_switchingTimeDL_1(0);
        public static final RRC_switchingTimeDL_1 N30US = new RRC_switchingTimeDL_1(1);
        public static final RRC_switchingTimeDL_1 N100US = new RRC_switchingTimeDL_1(2);
        public static final RRC_switchingTimeDL_1 N140US = new RRC_switchingTimeDL_1(3);
        public static final RRC_switchingTimeDL_1 N200US = new RRC_switchingTimeDL_1(4);
        public static final RRC_switchingTimeDL_1 N300US = new RRC_switchingTimeDL_1(5);
        public static final RRC_switchingTimeDL_1 N500US = new RRC_switchingTimeDL_1(6);
        public static final RRC_switchingTimeDL_1 N900US = new RRC_switchingTimeDL_1(7);
    
        private RRC_switchingTimeDL_1(long value) {
            super(value);
        }
    }
}

