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

public class RRC_CA_ParametersEUTRA extends AsnSequence {
    public RRC_multipleTimingAdvance multipleTimingAdvance; // optional
    public RRC_simultaneousRx_Tx simultaneousRx_Tx; // optional
    public AsnBitString supportedNAICS_2CRS_AP; // optional, SIZE(1..8)
    public RRC_additionalRx_Tx_PerformanceReq additionalRx_Tx_PerformanceReq; // optional
    public RRC_ue_CA_PowerClass_N ue_CA_PowerClass_N; // optional
    public AsnBitString supportedBandwidthCombinationSetEUTRA_v1530; // optional, SIZE(1..32)

    public static class RRC_additionalRx_Tx_PerformanceReq extends AsnEnumerated {
        public static final RRC_additionalRx_Tx_PerformanceReq SUPPORTED = new RRC_additionalRx_Tx_PerformanceReq(0);
    
        private RRC_additionalRx_Tx_PerformanceReq(long value) {
            super(value);
        }
    }

    public static class RRC_simultaneousRx_Tx extends AsnEnumerated {
        public static final RRC_simultaneousRx_Tx SUPPORTED = new RRC_simultaneousRx_Tx(0);
    
        private RRC_simultaneousRx_Tx(long value) {
            super(value);
        }
    }

    public static class RRC_multipleTimingAdvance extends AsnEnumerated {
        public static final RRC_multipleTimingAdvance SUPPORTED = new RRC_multipleTimingAdvance(0);
    
        private RRC_multipleTimingAdvance(long value) {
            super(value);
        }
    }

    public static class RRC_ue_CA_PowerClass_N extends AsnEnumerated {
        public static final RRC_ue_CA_PowerClass_N CLASS2 = new RRC_ue_CA_PowerClass_N(0);
    
        private RRC_ue_CA_PowerClass_N(long value) {
            super(value);
        }
    }
}

