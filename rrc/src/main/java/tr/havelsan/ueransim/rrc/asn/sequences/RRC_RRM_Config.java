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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultList2NR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultServFreqListEUTRA_SCG;

public class RRC_RRM_Config extends AsnSequence {
    public RRC_ue_InactiveTime ue_InactiveTime; // optional
    public RRC_MeasResultList2NR candidateCellInfoList; // optional
    public RRC_ext1_12 ext1; // optional

    public static class RRC_ext1_12 extends AsnSequence {
        public RRC_MeasResultServFreqListEUTRA_SCG candidateCellInfoListSN_EUTRA; // optional
    }

    public static class RRC_ue_InactiveTime extends AsnEnumerated {
        public static final RRC_ue_InactiveTime S1 = new RRC_ue_InactiveTime(0);
        public static final RRC_ue_InactiveTime S2 = new RRC_ue_InactiveTime(1);
        public static final RRC_ue_InactiveTime S3 = new RRC_ue_InactiveTime(2);
        public static final RRC_ue_InactiveTime S5 = new RRC_ue_InactiveTime(3);
        public static final RRC_ue_InactiveTime S7 = new RRC_ue_InactiveTime(4);
        public static final RRC_ue_InactiveTime S10 = new RRC_ue_InactiveTime(5);
        public static final RRC_ue_InactiveTime S15 = new RRC_ue_InactiveTime(6);
        public static final RRC_ue_InactiveTime S20 = new RRC_ue_InactiveTime(7);
        public static final RRC_ue_InactiveTime S25 = new RRC_ue_InactiveTime(8);
        public static final RRC_ue_InactiveTime S30 = new RRC_ue_InactiveTime(9);
        public static final RRC_ue_InactiveTime S40 = new RRC_ue_InactiveTime(10);
        public static final RRC_ue_InactiveTime S50 = new RRC_ue_InactiveTime(11);
        public static final RRC_ue_InactiveTime MIN1 = new RRC_ue_InactiveTime(12);
        public static final RRC_ue_InactiveTime MIN1S20 = new RRC_ue_InactiveTime(13);
        public static final RRC_ue_InactiveTime MIN1S40 = new RRC_ue_InactiveTime(14);
        public static final RRC_ue_InactiveTime MIN2 = new RRC_ue_InactiveTime(15);
        public static final RRC_ue_InactiveTime MIN2S30 = new RRC_ue_InactiveTime(16);
        public static final RRC_ue_InactiveTime MIN3 = new RRC_ue_InactiveTime(17);
        public static final RRC_ue_InactiveTime MIN3S30 = new RRC_ue_InactiveTime(18);
        public static final RRC_ue_InactiveTime MIN4 = new RRC_ue_InactiveTime(19);
        public static final RRC_ue_InactiveTime MIN5 = new RRC_ue_InactiveTime(20);
        public static final RRC_ue_InactiveTime MIN6 = new RRC_ue_InactiveTime(21);
        public static final RRC_ue_InactiveTime MIN7 = new RRC_ue_InactiveTime(22);
        public static final RRC_ue_InactiveTime MIN8 = new RRC_ue_InactiveTime(23);
        public static final RRC_ue_InactiveTime MIN9 = new RRC_ue_InactiveTime(24);
        public static final RRC_ue_InactiveTime MIN10 = new RRC_ue_InactiveTime(25);
        public static final RRC_ue_InactiveTime MIN12 = new RRC_ue_InactiveTime(26);
        public static final RRC_ue_InactiveTime MIN14 = new RRC_ue_InactiveTime(27);
        public static final RRC_ue_InactiveTime MIN17 = new RRC_ue_InactiveTime(28);
        public static final RRC_ue_InactiveTime MIN20 = new RRC_ue_InactiveTime(29);
        public static final RRC_ue_InactiveTime MIN24 = new RRC_ue_InactiveTime(30);
        public static final RRC_ue_InactiveTime MIN28 = new RRC_ue_InactiveTime(31);
        public static final RRC_ue_InactiveTime MIN33 = new RRC_ue_InactiveTime(32);
        public static final RRC_ue_InactiveTime MIN38 = new RRC_ue_InactiveTime(33);
        public static final RRC_ue_InactiveTime MIN44 = new RRC_ue_InactiveTime(34);
        public static final RRC_ue_InactiveTime MIN50 = new RRC_ue_InactiveTime(35);
        public static final RRC_ue_InactiveTime HR1 = new RRC_ue_InactiveTime(36);
        public static final RRC_ue_InactiveTime HR1MIN30 = new RRC_ue_InactiveTime(37);
        public static final RRC_ue_InactiveTime HR2 = new RRC_ue_InactiveTime(38);
        public static final RRC_ue_InactiveTime HR2MIN30 = new RRC_ue_InactiveTime(39);
        public static final RRC_ue_InactiveTime HR3 = new RRC_ue_InactiveTime(40);
        public static final RRC_ue_InactiveTime HR3MIN30 = new RRC_ue_InactiveTime(41);
        public static final RRC_ue_InactiveTime HR4 = new RRC_ue_InactiveTime(42);
        public static final RRC_ue_InactiveTime HR5 = new RRC_ue_InactiveTime(43);
        public static final RRC_ue_InactiveTime HR6 = new RRC_ue_InactiveTime(44);
        public static final RRC_ue_InactiveTime HR8 = new RRC_ue_InactiveTime(45);
        public static final RRC_ue_InactiveTime HR10 = new RRC_ue_InactiveTime(46);
        public static final RRC_ue_InactiveTime HR13 = new RRC_ue_InactiveTime(47);
        public static final RRC_ue_InactiveTime HR16 = new RRC_ue_InactiveTime(48);
        public static final RRC_ue_InactiveTime HR20 = new RRC_ue_InactiveTime(49);
        public static final RRC_ue_InactiveTime DAY1 = new RRC_ue_InactiveTime(50);
        public static final RRC_ue_InactiveTime DAY1HR12 = new RRC_ue_InactiveTime(51);
        public static final RRC_ue_InactiveTime DAY2 = new RRC_ue_InactiveTime(52);
        public static final RRC_ue_InactiveTime DAY2HR12 = new RRC_ue_InactiveTime(53);
        public static final RRC_ue_InactiveTime DAY3 = new RRC_ue_InactiveTime(54);
        public static final RRC_ue_InactiveTime DAY4 = new RRC_ue_InactiveTime(55);
        public static final RRC_ue_InactiveTime DAY5 = new RRC_ue_InactiveTime(56);
        public static final RRC_ue_InactiveTime DAY7 = new RRC_ue_InactiveTime(57);
        public static final RRC_ue_InactiveTime DAY10 = new RRC_ue_InactiveTime(58);
        public static final RRC_ue_InactiveTime DAY14 = new RRC_ue_InactiveTime(59);
        public static final RRC_ue_InactiveTime DAY19 = new RRC_ue_InactiveTime(60);
        public static final RRC_ue_InactiveTime DAY24 = new RRC_ue_InactiveTime(61);
        public static final RRC_ue_InactiveTime DAY30 = new RRC_ue_InactiveTime(62);
        public static final RRC_ue_InactiveTime DAYMORETHAN30 = new RRC_ue_InactiveTime(63);
    
        private RRC_ue_InactiveTime(long value) {
            super(value);
        }
    }
}

