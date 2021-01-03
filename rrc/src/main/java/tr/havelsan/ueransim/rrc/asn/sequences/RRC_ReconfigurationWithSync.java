/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_ReconfigurationWithSync extends AsnSequence {
    public RRC_ServingCellConfigCommon spCellConfigCommon; // optional
    public RRC_RNTI_Value newUE_Identity; // mandatory
    public RRC_t304 t304; // mandatory
    public RRC_rach_ConfigDedicated rach_ConfigDedicated; // optional
    public RRC_ext1_54 ext1; // optional

    public static class RRC_rach_ConfigDedicated extends AsnChoice {
        public RRC_RACH_ConfigDedicated uplink;
        public RRC_RACH_ConfigDedicated supplementaryUplink;
    }

    public static class RRC_t304 extends AsnEnumerated {
        public static final RRC_t304 MS50 = new RRC_t304(0);
        public static final RRC_t304 MS100 = new RRC_t304(1);
        public static final RRC_t304 MS150 = new RRC_t304(2);
        public static final RRC_t304 MS200 = new RRC_t304(3);
        public static final RRC_t304 MS500 = new RRC_t304(4);
        public static final RRC_t304 MS1000 = new RRC_t304(5);
        public static final RRC_t304 MS2000 = new RRC_t304(6);
        public static final RRC_t304 MS10000 = new RRC_t304(7);
    
        private RRC_t304(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_54 extends AsnSequence {
        public RRC_SSB_MTC smtc; // optional
    }
}

