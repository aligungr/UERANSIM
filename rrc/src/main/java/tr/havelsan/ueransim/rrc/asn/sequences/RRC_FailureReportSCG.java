/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultFreqList;

public class RRC_FailureReportSCG extends AsnSequence {
    public RRC_failureType_1 failureType; // mandatory
    public RRC_MeasResultFreqList measResultFreqList; // optional
    public AsnOctetString measResultSCG_Failure; // optional, SIZE(0..MAX)

    public static class RRC_failureType_1 extends AsnEnumerated {
        public static final RRC_failureType_1 T310_EXPIRY = new RRC_failureType_1(0);
        public static final RRC_failureType_1 RANDOMACCESSPROBLEM = new RRC_failureType_1(1);
        public static final RRC_failureType_1 RLC_MAXNUMRETX = new RRC_failureType_1(2);
        public static final RRC_failureType_1 SYNCHRECONFIGFAILURESCG = new RRC_failureType_1(3);
        public static final RRC_failureType_1 SCG_RECONFIGFAILURE = new RRC_failureType_1(4);
        public static final RRC_failureType_1 SRB3_INTEGRITYFAILURE = new RRC_failureType_1(5);
        public static final RRC_failureType_1 SPARE2 = new RRC_failureType_1(6);
        public static final RRC_failureType_1 SPARE1 = new RRC_failureType_1(7);
    
        private RRC_failureType_1(long value) {
            super(value);
        }
    }
}

