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
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultFreqListFailMRDC;

public class RRC_FailureReportSCG_EUTRA extends AsnSequence {
    public RRC_failureType_2 failureType; // mandatory
    public RRC_MeasResultFreqListFailMRDC measResultFreqListMRDC; // optional
    public AsnOctetString measResultSCG_FailureMRDC; // optional

    public static class RRC_failureType_2 extends AsnEnumerated {
        public static final RRC_failureType_2 T313_EXPIRY = new RRC_failureType_2(0);
        public static final RRC_failureType_2 RANDOMACCESSPROBLEM = new RRC_failureType_2(1);
        public static final RRC_failureType_2 RLC_MAXNUMRETX = new RRC_failureType_2(2);
        public static final RRC_failureType_2 SCG_CHANGEFAILURE = new RRC_failureType_2(3);
        public static final RRC_failureType_2 SPARE4 = new RRC_failureType_2(4);
        public static final RRC_failureType_2 SPARE3 = new RRC_failureType_2(5);
        public static final RRC_failureType_2 SPARE2 = new RRC_failureType_2(6);
        public static final RRC_failureType_2 SPARE1 = new RRC_failureType_2(7);
    
        private RRC_failureType_2(long value) {
            super(value);
        }
    }
}

