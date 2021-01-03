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
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PollByte;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_PollPDU;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SN_FieldLengthAM;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_T_PollRetransmit;

public class RRC_UL_AM_RLC extends AsnSequence {
    public RRC_SN_FieldLengthAM sn_FieldLength; // optional
    public RRC_T_PollRetransmit t_PollRetransmit; // mandatory
    public RRC_PollPDU pollPDU; // mandatory
    public RRC_PollByte pollByte; // mandatory
    public RRC_maxRetxThreshold maxRetxThreshold; // mandatory

    public static class RRC_maxRetxThreshold extends AsnEnumerated {
        public static final RRC_maxRetxThreshold T1 = new RRC_maxRetxThreshold(0);
        public static final RRC_maxRetxThreshold T2 = new RRC_maxRetxThreshold(1);
        public static final RRC_maxRetxThreshold T3 = new RRC_maxRetxThreshold(2);
        public static final RRC_maxRetxThreshold T4 = new RRC_maxRetxThreshold(3);
        public static final RRC_maxRetxThreshold T6 = new RRC_maxRetxThreshold(4);
        public static final RRC_maxRetxThreshold T8 = new RRC_maxRetxThreshold(5);
        public static final RRC_maxRetxThreshold T16 = new RRC_maxRetxThreshold(6);
        public static final RRC_maxRetxThreshold T32 = new RRC_maxRetxThreshold(7);
    
        private RRC_maxRetxThreshold(long value) {
            super(value);
        }
    }
}

