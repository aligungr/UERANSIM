/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;

public class RRC_SPS_Config extends AsnSequence {
    public RRC_periodicity_1 periodicity; // mandatory
    public AsnInteger nrofHARQ_Processes; // mandatory, VALUE(1..8)
    public RRC_PUCCH_ResourceId n1PUCCH_AN; // optional
    public RRC_mcs_Table_3 mcs_Table; // optional

    public static class RRC_periodicity_1 extends AsnEnumerated {
        public static final RRC_periodicity_1 MS10 = new RRC_periodicity_1(0);
        public static final RRC_periodicity_1 MS20 = new RRC_periodicity_1(1);
        public static final RRC_periodicity_1 MS32 = new RRC_periodicity_1(2);
        public static final RRC_periodicity_1 MS40 = new RRC_periodicity_1(3);
        public static final RRC_periodicity_1 MS64 = new RRC_periodicity_1(4);
        public static final RRC_periodicity_1 MS80 = new RRC_periodicity_1(5);
        public static final RRC_periodicity_1 MS128 = new RRC_periodicity_1(6);
        public static final RRC_periodicity_1 MS160 = new RRC_periodicity_1(7);
        public static final RRC_periodicity_1 MS320 = new RRC_periodicity_1(8);
        public static final RRC_periodicity_1 MS640 = new RRC_periodicity_1(9);
        public static final RRC_periodicity_1 SPARE6 = new RRC_periodicity_1(10);
        public static final RRC_periodicity_1 SPARE5 = new RRC_periodicity_1(11);
        public static final RRC_periodicity_1 SPARE4 = new RRC_periodicity_1(12);
        public static final RRC_periodicity_1 SPARE3 = new RRC_periodicity_1(13);
        public static final RRC_periodicity_1 SPARE2 = new RRC_periodicity_1(14);
        public static final RRC_periodicity_1 SPARE1 = new RRC_periodicity_1(15);
    
        private RRC_periodicity_1(long value) {
            super(value);
        }
    }

    public static class RRC_mcs_Table_3 extends AsnEnumerated {
        public static final RRC_mcs_Table_3 QAM64LOWSE = new RRC_mcs_Table_3(0);
    
        private RRC_mcs_Table_3(long value) {
            super(value);
        }
    }
}

