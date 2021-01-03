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

public class RRC_MeasAndMobParametersXDD_Diff extends AsnSequence {
    public RRC_intraAndInterF_MeasAndReport intraAndInterF_MeasAndReport; // optional
    public RRC_eventA_MeasAndReport eventA_MeasAndReport; // optional
    public RRC_ext1_8 ext1; // optional

    public static class RRC_ext1_8 extends AsnSequence {
        public RRC_handoverInterF_2 handoverInterF; // optional
        public RRC_handoverLTE_EPC_1 handoverLTE_EPC; // optional
        public RRC_handoverLTE_5GC_1 handoverLTE_5GC; // optional
    
        public static class RRC_handoverLTE_5GC_1 extends AsnEnumerated {
            public static final RRC_handoverLTE_5GC_1 SUPPORTED = new RRC_handoverLTE_5GC_1(0);
        
            private RRC_handoverLTE_5GC_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_handoverLTE_EPC_1 extends AsnEnumerated {
            public static final RRC_handoverLTE_EPC_1 SUPPORTED = new RRC_handoverLTE_EPC_1(0);
        
            private RRC_handoverLTE_EPC_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_handoverInterF_2 extends AsnEnumerated {
            public static final RRC_handoverInterF_2 SUPPORTED = new RRC_handoverInterF_2(0);
        
            private RRC_handoverInterF_2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_eventA_MeasAndReport extends AsnEnumerated {
        public static final RRC_eventA_MeasAndReport SUPPORTED = new RRC_eventA_MeasAndReport(0);
    
        private RRC_eventA_MeasAndReport(long value) {
            super(value);
        }
    }

    public static class RRC_intraAndInterF_MeasAndReport extends AsnEnumerated {
        public static final RRC_intraAndInterF_MeasAndReport SUPPORTED = new RRC_intraAndInterF_MeasAndReport(0);
    
        private RRC_intraAndInterF_MeasAndReport(long value) {
            super(value);
        }
    }
}

