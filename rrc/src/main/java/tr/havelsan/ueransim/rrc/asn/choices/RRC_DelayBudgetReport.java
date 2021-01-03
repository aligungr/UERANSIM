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

public class RRC_DelayBudgetReport extends AsnChoice {
    public RRC_type1_1 type1;

    public static class RRC_type1_1 extends AsnEnumerated {
        public static final RRC_type1_1 MSMINUS1280 = new RRC_type1_1(0);
        public static final RRC_type1_1 MSMINUS640 = new RRC_type1_1(1);
        public static final RRC_type1_1 MSMINUS320 = new RRC_type1_1(2);
        public static final RRC_type1_1 MSMINUS160 = new RRC_type1_1(3);
        public static final RRC_type1_1 MSMINUS80 = new RRC_type1_1(4);
        public static final RRC_type1_1 MSMINUS60 = new RRC_type1_1(5);
        public static final RRC_type1_1 MSMINUS40 = new RRC_type1_1(6);
        public static final RRC_type1_1 MSMINUS20 = new RRC_type1_1(7);
        public static final RRC_type1_1 MS0 = new RRC_type1_1(8);
        public static final RRC_type1_1 MS20 = new RRC_type1_1(9);
        public static final RRC_type1_1 MS40 = new RRC_type1_1(10);
        public static final RRC_type1_1 MS60 = new RRC_type1_1(11);
        public static final RRC_type1_1 MS80 = new RRC_type1_1(12);
        public static final RRC_type1_1 MS160 = new RRC_type1_1(13);
        public static final RRC_type1_1 MS320 = new RRC_type1_1(14);
        public static final RRC_type1_1 MS640 = new RRC_type1_1(15);
        public static final RRC_type1_1 MS1280 = new RRC_type1_1(16);
    
        private RRC_type1_1(long value) {
            super(value);
        }
    }
}

