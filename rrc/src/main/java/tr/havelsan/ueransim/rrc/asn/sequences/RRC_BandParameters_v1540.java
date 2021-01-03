/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_BandParameters_v1540 extends AsnSequence {
    public RRC_srs_CarrierSwitch srs_CarrierSwitch; // optional
    public RRC_srs_TxSwitch srs_TxSwitch; // optional

    public static class RRC_srs_CarrierSwitch extends AsnChoice {
        public RRC_nr_2 nr;
        public RRC_eutra_3 eutra;
    
        public static class RRC_nr_2 extends AsnSequence {
            public RRC_srs_SwitchingTimesListNR srs_SwitchingTimesListNR; // mandatory, SIZE(1..32)
        
            // SIZE(1..32)
            public static class RRC_srs_SwitchingTimesListNR extends AsnSequenceOf<RRC_SRS_SwitchingTimeNR> {
            }
        }
    
        public static class RRC_eutra_3 extends AsnSequence {
            public RRC_srs_SwitchingTimesListEUTRA srs_SwitchingTimesListEUTRA; // mandatory, SIZE(1..32)
        
            // SIZE(1..32)
            public static class RRC_srs_SwitchingTimesListEUTRA extends AsnSequenceOf<RRC_SRS_SwitchingTimeEUTRA> {
            }
        }
    }

    public static class RRC_srs_TxSwitch extends AsnSequence {
        public RRC_supportedSRS_TxPortSwitch_2 supportedSRS_TxPortSwitch; // mandatory
        public AsnInteger txSwitchImpactToRx; // optional, VALUE(1..32)
        public AsnInteger txSwitchWithAnotherBand; // optional, VALUE(1..32)
    
        public static class RRC_supportedSRS_TxPortSwitch_2 extends AsnEnumerated {
            public static final RRC_supportedSRS_TxPortSwitch_2 T1R2 = new RRC_supportedSRS_TxPortSwitch_2(0);
            public static final RRC_supportedSRS_TxPortSwitch_2 T1R4 = new RRC_supportedSRS_TxPortSwitch_2(1);
            public static final RRC_supportedSRS_TxPortSwitch_2 T2R4 = new RRC_supportedSRS_TxPortSwitch_2(2);
            public static final RRC_supportedSRS_TxPortSwitch_2 T1R4_T2R4 = new RRC_supportedSRS_TxPortSwitch_2(3);
            public static final RRC_supportedSRS_TxPortSwitch_2 T1R1 = new RRC_supportedSRS_TxPortSwitch_2(4);
            public static final RRC_supportedSRS_TxPortSwitch_2 T2R2 = new RRC_supportedSRS_TxPortSwitch_2(5);
            public static final RRC_supportedSRS_TxPortSwitch_2 T4R4 = new RRC_supportedSRS_TxPortSwitch_2(6);
            public static final RRC_supportedSRS_TxPortSwitch_2 NOTSUPPORTED = new RRC_supportedSRS_TxPortSwitch_2(7);
        
            private RRC_supportedSRS_TxPortSwitch_2(long value) {
                super(value);
            }
        }
    }
}

