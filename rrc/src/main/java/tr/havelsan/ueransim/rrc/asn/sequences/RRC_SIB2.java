/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_ToMeasure;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_CellReselectionSubPriority;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_RangeToBestCell;
import tr.havelsan.ueransim.rrc.asn.integers.*;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MultiFrequencyBandListNR_SIB;

public class RRC_SIB2 extends AsnSequence {
    public RRC_cellReselectionInfoCommon cellReselectionInfoCommon; // mandatory
    public RRC_cellReselectionServingFreqInfo cellReselectionServingFreqInfo; // mandatory
    public RRC_intraFreqCellReselectionInfo intraFreqCellReselectionInfo; // mandatory

    public static class RRC_cellReselectionInfoCommon extends AsnSequence {
        public AsnInteger nrofSS_BlocksToAverage; // optional, VALUE(2..16)
        public RRC_ThresholdNR absThreshSS_BlocksConsolidation; // optional
        public RRC_RangeToBestCell rangeToBestCell; // optional
        public RRC_q_Hyst q_Hyst; // mandatory
        public RRC_speedStateReselectionPars speedStateReselectionPars; // optional
    
        public static class RRC_q_Hyst extends AsnEnumerated {
            public static final RRC_q_Hyst DB0 = new RRC_q_Hyst(0);
            public static final RRC_q_Hyst DB1 = new RRC_q_Hyst(1);
            public static final RRC_q_Hyst DB2 = new RRC_q_Hyst(2);
            public static final RRC_q_Hyst DB3 = new RRC_q_Hyst(3);
            public static final RRC_q_Hyst DB4 = new RRC_q_Hyst(4);
            public static final RRC_q_Hyst DB5 = new RRC_q_Hyst(5);
            public static final RRC_q_Hyst DB6 = new RRC_q_Hyst(6);
            public static final RRC_q_Hyst DB8 = new RRC_q_Hyst(7);
            public static final RRC_q_Hyst DB10 = new RRC_q_Hyst(8);
            public static final RRC_q_Hyst DB12 = new RRC_q_Hyst(9);
            public static final RRC_q_Hyst DB14 = new RRC_q_Hyst(10);
            public static final RRC_q_Hyst DB16 = new RRC_q_Hyst(11);
            public static final RRC_q_Hyst DB18 = new RRC_q_Hyst(12);
            public static final RRC_q_Hyst DB20 = new RRC_q_Hyst(13);
            public static final RRC_q_Hyst DB22 = new RRC_q_Hyst(14);
            public static final RRC_q_Hyst DB24 = new RRC_q_Hyst(15);
        
            private RRC_q_Hyst(long value) {
                super(value);
            }
        }
    
        public static class RRC_speedStateReselectionPars extends AsnSequence {
            public RRC_MobilityStateParameters mobilityStateParameters; // mandatory
            public RRC_q_HystSF q_HystSF; // mandatory
        
            public static class RRC_q_HystSF extends AsnSequence {
                public RRC_sf_Medium_1 sf_Medium; // mandatory
                public RRC_sf_High_1 sf_High; // mandatory
            
                public static class RRC_sf_Medium_1 extends AsnEnumerated {
                    public static final RRC_sf_Medium_1 DB_6 = new RRC_sf_Medium_1(0);
                    public static final RRC_sf_Medium_1 DB_4 = new RRC_sf_Medium_1(1);
                    public static final RRC_sf_Medium_1 DB_2 = new RRC_sf_Medium_1(2);
                    public static final RRC_sf_Medium_1 DB0 = new RRC_sf_Medium_1(3);
                
                    private RRC_sf_Medium_1(long value) {
                        super(value);
                    }
                }
            
                public static class RRC_sf_High_1 extends AsnEnumerated {
                    public static final RRC_sf_High_1 DB_6 = new RRC_sf_High_1(0);
                    public static final RRC_sf_High_1 DB_4 = new RRC_sf_High_1(1);
                    public static final RRC_sf_High_1 DB_2 = new RRC_sf_High_1(2);
                    public static final RRC_sf_High_1 DB0 = new RRC_sf_High_1(3);
                
                    private RRC_sf_High_1(long value) {
                        super(value);
                    }
                }
            }
        }
    }

    public static class RRC_intraFreqCellReselectionInfo extends AsnSequence {
        public RRC_Q_RxLevMin q_RxLevMin; // mandatory
        public RRC_Q_RxLevMin q_RxLevMinSUL; // optional
        public RRC_Q_QualMin q_QualMin; // optional
        public RRC_ReselectionThreshold s_IntraSearchP; // mandatory
        public RRC_ReselectionThresholdQ s_IntraSearchQ; // optional
        public RRC_T_Reselection t_ReselectionNR; // mandatory
        public RRC_MultiFrequencyBandListNR_SIB frequencyBandList; // optional
        public RRC_MultiFrequencyBandListNR_SIB frequencyBandListSUL; // optional
        public RRC_P_Max p_Max; // optional
        public RRC_SSB_MTC smtc; // optional
        public RRC_SS_RSSI_Measurement ss_RSSI_Measurement; // optional
        public RRC_SSB_ToMeasure ssb_ToMeasure; // optional
        public AsnBoolean deriveSSB_IndexFromCell; // mandatory
        public RRC_ext1_36 ext1; // optional
    
        public static class RRC_ext1_36 extends AsnSequence {
            public RRC_SpeedStateScaleFactors t_ReselectionNR_SF; // optional
        }
    }

    public static class RRC_cellReselectionServingFreqInfo extends AsnSequence {
        public RRC_ReselectionThreshold s_NonIntraSearchP; // optional
        public RRC_ReselectionThresholdQ s_NonIntraSearchQ; // optional
        public RRC_ReselectionThreshold threshServingLowP; // mandatory
        public RRC_ReselectionThresholdQ threshServingLowQ; // optional
        public RRC_CellReselectionPriority cellReselectionPriority; // mandatory
        public RRC_CellReselectionSubPriority cellReselectionSubPriority; // optional
    }
}

