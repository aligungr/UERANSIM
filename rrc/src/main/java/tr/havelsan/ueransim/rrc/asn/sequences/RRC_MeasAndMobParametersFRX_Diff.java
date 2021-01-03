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

public class RRC_MeasAndMobParametersFRX_Diff extends AsnSequence {
    public RRC_ss_SINR_Meas ss_SINR_Meas; // optional
    public RRC_csi_RSRP_AndRSRQ_MeasWithSSB csi_RSRP_AndRSRQ_MeasWithSSB; // optional
    public RRC_csi_RSRP_AndRSRQ_MeasWithoutSSB csi_RSRP_AndRSRQ_MeasWithoutSSB; // optional
    public RRC_csi_SINR_Meas csi_SINR_Meas; // optional
    public RRC_csi_RS_RLM csi_RS_RLM; // optional
    public RRC_ext1_27 ext1; // optional
    public RRC_ext2_3 ext2; // optional
    public RRC_ext3_6 ext3; // optional

    public static class RRC_ext2_3 extends AsnSequence {
        public RRC_maxNumberResource_CSI_RS_RLM maxNumberResource_CSI_RS_RLM; // optional
    
        public static class RRC_maxNumberResource_CSI_RS_RLM extends AsnEnumerated {
            public static final RRC_maxNumberResource_CSI_RS_RLM N2 = new RRC_maxNumberResource_CSI_RS_RLM(0);
            public static final RRC_maxNumberResource_CSI_RS_RLM N4 = new RRC_maxNumberResource_CSI_RS_RLM(1);
            public static final RRC_maxNumberResource_CSI_RS_RLM N6 = new RRC_maxNumberResource_CSI_RS_RLM(2);
            public static final RRC_maxNumberResource_CSI_RS_RLM N8 = new RRC_maxNumberResource_CSI_RS_RLM(3);
        
            private RRC_maxNumberResource_CSI_RS_RLM(long value) {
                super(value);
            }
        }
    }

    public static class RRC_csi_RS_RLM extends AsnEnumerated {
        public static final RRC_csi_RS_RLM SUPPORTED = new RRC_csi_RS_RLM(0);
    
        private RRC_csi_RS_RLM(long value) {
            super(value);
        }
    }

    public static class RRC_csi_SINR_Meas extends AsnEnumerated {
        public static final RRC_csi_SINR_Meas SUPPORTED = new RRC_csi_SINR_Meas(0);
    
        private RRC_csi_SINR_Meas(long value) {
            super(value);
        }
    }

    public static class RRC_csi_RSRP_AndRSRQ_MeasWithoutSSB extends AsnEnumerated {
        public static final RRC_csi_RSRP_AndRSRQ_MeasWithoutSSB SUPPORTED = new RRC_csi_RSRP_AndRSRQ_MeasWithoutSSB(0);
    
        private RRC_csi_RSRP_AndRSRQ_MeasWithoutSSB(long value) {
            super(value);
        }
    }

    public static class RRC_ss_SINR_Meas extends AsnEnumerated {
        public static final RRC_ss_SINR_Meas SUPPORTED = new RRC_ss_SINR_Meas(0);
    
        private RRC_ss_SINR_Meas(long value) {
            super(value);
        }
    }

    public static class RRC_csi_RSRP_AndRSRQ_MeasWithSSB extends AsnEnumerated {
        public static final RRC_csi_RSRP_AndRSRQ_MeasWithSSB SUPPORTED = new RRC_csi_RSRP_AndRSRQ_MeasWithSSB(0);
    
        private RRC_csi_RSRP_AndRSRQ_MeasWithSSB(long value) {
            super(value);
        }
    }

    public static class RRC_ext1_27 extends AsnSequence {
        public RRC_handoverInterF_1 handoverInterF; // optional
        public RRC_handoverLTE_EPC_2 handoverLTE_EPC; // optional
        public RRC_handoverLTE_5GC_2 handoverLTE_5GC; // optional
    
        public static class RRC_handoverLTE_EPC_2 extends AsnEnumerated {
            public static final RRC_handoverLTE_EPC_2 SUPPORTED = new RRC_handoverLTE_EPC_2(0);
        
            private RRC_handoverLTE_EPC_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_handoverInterF_1 extends AsnEnumerated {
            public static final RRC_handoverInterF_1 SUPPORTED = new RRC_handoverInterF_1(0);
        
            private RRC_handoverInterF_1(long value) {
                super(value);
            }
        }
    
        public static class RRC_handoverLTE_5GC_2 extends AsnEnumerated {
            public static final RRC_handoverLTE_5GC_2 SUPPORTED = new RRC_handoverLTE_5GC_2(0);
        
            private RRC_handoverLTE_5GC_2(long value) {
                super(value);
            }
        }
    }

    public static class RRC_ext3_6 extends AsnSequence {
        public RRC_simultaneousRxDataSSB_DiffNumerology_2 simultaneousRxDataSSB_DiffNumerology; // optional
    
        public static class RRC_simultaneousRxDataSSB_DiffNumerology_2 extends AsnEnumerated {
            public static final RRC_simultaneousRxDataSSB_DiffNumerology_2 SUPPORTED = new RRC_simultaneousRxDataSSB_DiffNumerology_2(0);
        
            private RRC_simultaneousRxDataSSB_DiffNumerology_2(long value) {
                super(value);
            }
        }
    }
}

