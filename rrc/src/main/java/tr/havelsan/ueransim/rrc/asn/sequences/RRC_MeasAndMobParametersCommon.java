/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasAndMobParametersCommon extends AsnSequence {
    public AsnBitString supportedGapPattern; // optional, SIZE(22)
    public RRC_ssb_RLM ssb_RLM; // optional
    public RRC_ssb_AndCSI_RS_RLM ssb_AndCSI_RS_RLM; // optional
    public RRC_ext1_9 ext1; // optional
    public RRC_ext2_6 ext2; // optional
    public RRC_ext3_2 ext3; // optional

    public static class RRC_ext1_9 extends AsnSequence {
        public RRC_eventB_MeasAndReport eventB_MeasAndReport; // optional
        public RRC_handoverFDD_TDD handoverFDD_TDD; // optional
        public RRC_eutra_CGI_Reporting eutra_CGI_Reporting; // optional
        public RRC_nr_CGI_Reporting nr_CGI_Reporting; // optional
    
        public static class RRC_handoverFDD_TDD extends AsnEnumerated {
            public static final RRC_handoverFDD_TDD SUPPORTED = new RRC_handoverFDD_TDD(0);
        
            private RRC_handoverFDD_TDD(long value) {
                super(value);
            }
        }
    
        public static class RRC_eventB_MeasAndReport extends AsnEnumerated {
            public static final RRC_eventB_MeasAndReport SUPPORTED = new RRC_eventB_MeasAndReport(0);
        
            private RRC_eventB_MeasAndReport(long value) {
                super(value);
            }
        }
    
        public static class RRC_eutra_CGI_Reporting extends AsnEnumerated {
            public static final RRC_eutra_CGI_Reporting SUPPORTED = new RRC_eutra_CGI_Reporting(0);
        
            private RRC_eutra_CGI_Reporting(long value) {
                super(value);
            }
        }
    
        public static class RRC_nr_CGI_Reporting extends AsnEnumerated {
            public static final RRC_nr_CGI_Reporting SUPPORTED = new RRC_nr_CGI_Reporting(0);
        
            private RRC_nr_CGI_Reporting(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ssb_RLM extends AsnEnumerated {
        public static final RRC_ssb_RLM SUPPORTED = new RRC_ssb_RLM(0);
    
        private RRC_ssb_RLM(long value) {
            super(value);
        }
    }

    public static class RRC_ext2_6 extends AsnSequence {
        public RRC_independentGapConfig_2 independentGapConfig; // optional
        public RRC_periodicEUTRA_MeasAndReport periodicEUTRA_MeasAndReport; // optional
        public RRC_handoverFR1_FR2 handoverFR1_FR2; // optional
        public RRC_maxNumberCSI_RS_RRM_RS_SINR maxNumberCSI_RS_RRM_RS_SINR; // optional
    
        public static class RRC_maxNumberCSI_RS_RRM_RS_SINR extends AsnEnumerated {
            public static final RRC_maxNumberCSI_RS_RRM_RS_SINR N4 = new RRC_maxNumberCSI_RS_RRM_RS_SINR(0);
            public static final RRC_maxNumberCSI_RS_RRM_RS_SINR N8 = new RRC_maxNumberCSI_RS_RRM_RS_SINR(1);
            public static final RRC_maxNumberCSI_RS_RRM_RS_SINR N16 = new RRC_maxNumberCSI_RS_RRM_RS_SINR(2);
            public static final RRC_maxNumberCSI_RS_RRM_RS_SINR N32 = new RRC_maxNumberCSI_RS_RRM_RS_SINR(3);
            public static final RRC_maxNumberCSI_RS_RRM_RS_SINR N64 = new RRC_maxNumberCSI_RS_RRM_RS_SINR(4);
            public static final RRC_maxNumberCSI_RS_RRM_RS_SINR N96 = new RRC_maxNumberCSI_RS_RRM_RS_SINR(5);
        
            private RRC_maxNumberCSI_RS_RRM_RS_SINR(long value) {
                super(value);
            }
        }
    
        public static class RRC_handoverFR1_FR2 extends AsnEnumerated {
            public static final RRC_handoverFR1_FR2 SUPPORTED = new RRC_handoverFR1_FR2(0);
        
            private RRC_handoverFR1_FR2(long value) {
                super(value);
            }
        }
    
        public static class RRC_independentGapConfig_2 extends AsnEnumerated {
            public static final RRC_independentGapConfig_2 SUPPORTED = new RRC_independentGapConfig_2(0);
        
            private RRC_independentGapConfig_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_periodicEUTRA_MeasAndReport extends AsnEnumerated {
            public static final RRC_periodicEUTRA_MeasAndReport SUPPORTED = new RRC_periodicEUTRA_MeasAndReport(0);
        
            private RRC_periodicEUTRA_MeasAndReport(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ext3_2 extends AsnSequence {
        public RRC_nr_CGI_Reporting_ENDC nr_CGI_Reporting_ENDC; // optional
    
        public static class RRC_nr_CGI_Reporting_ENDC extends AsnEnumerated {
            public static final RRC_nr_CGI_Reporting_ENDC SUPPORTED = new RRC_nr_CGI_Reporting_ENDC(0);
        
            private RRC_nr_CGI_Reporting_ENDC(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ssb_AndCSI_RS_RLM extends AsnEnumerated {
        public static final RRC_ssb_AndCSI_RS_RLM SUPPORTED = new RRC_ssb_AndCSI_RS_RLM(0);
    
        private RRC_ssb_AndCSI_RS_RLM(long value) {
            super(value);
        }
    }
}

