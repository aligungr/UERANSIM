/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RateMatchPatternLTE_CRS;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;

public class RRC_ServingCellConfigCommon extends AsnSequence {
    public RRC_PhysCellId physCellId; // optional
    public RRC_DownlinkConfigCommon downlinkConfigCommon; // optional
    public RRC_UplinkConfigCommon uplinkConfigCommon; // optional
    public RRC_UplinkConfigCommon supplementaryUplinkConfig; // optional
    public RRC_n_TimingAdvanceOffset_2 n_TimingAdvanceOffset; // optional
    public RRC_ssb_PositionsInBurst_2 ssb_PositionsInBurst; // optional
    public RRC_ssb_periodicityServingCell ssb_periodicityServingCell; // optional
    public RRC_dmrs_TypeA_Position_2 dmrs_TypeA_Position; // mandatory
    public RRC_SetupRelease_RateMatchPatternLTE_CRS lte_CRS_ToMatchAround; // optional
    public RRC_rateMatchPatternToAddModList_1 rateMatchPatternToAddModList; // optional, SIZE(1..4)
    public RRC_rateMatchPatternToReleaseList_2 rateMatchPatternToReleaseList; // optional, SIZE(1..4)
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing; // optional
    public RRC_TDD_UL_DL_ConfigCommon tdd_UL_DL_ConfigurationCommon; // optional
    public AsnInteger ss_PBCH_BlockPower; // mandatory, VALUE(-60..50)

    // SIZE(1..4)
    public static class RRC_rateMatchPatternToAddModList_1 extends AsnSequenceOf<RRC_RateMatchPattern> {
    }

    public static class RRC_n_TimingAdvanceOffset_2 extends AsnEnumerated {
        public static final RRC_n_TimingAdvanceOffset_2 N0 = new RRC_n_TimingAdvanceOffset_2(0);
        public static final RRC_n_TimingAdvanceOffset_2 N25600 = new RRC_n_TimingAdvanceOffset_2(1);
        public static final RRC_n_TimingAdvanceOffset_2 N39936 = new RRC_n_TimingAdvanceOffset_2(2);
    
        private RRC_n_TimingAdvanceOffset_2(long value) {
            super(value);
        }
    }

    // SIZE(1..4)
    public static class RRC_rateMatchPatternToReleaseList_2 extends AsnSequenceOf<RRC_RateMatchPatternId> {
    }

    public static class RRC_ssb_PositionsInBurst_2 extends AsnChoice {
        public AsnBitString shortBitmap; // SIZE(4)
        public AsnBitString mediumBitmap; // SIZE(8)
        public AsnBitString longBitmap; // SIZE(64)
    }

    public static class RRC_ssb_periodicityServingCell extends AsnEnumerated {
        public static final RRC_ssb_periodicityServingCell MS5 = new RRC_ssb_periodicityServingCell(0);
        public static final RRC_ssb_periodicityServingCell MS10 = new RRC_ssb_periodicityServingCell(1);
        public static final RRC_ssb_periodicityServingCell MS20 = new RRC_ssb_periodicityServingCell(2);
        public static final RRC_ssb_periodicityServingCell MS40 = new RRC_ssb_periodicityServingCell(3);
        public static final RRC_ssb_periodicityServingCell MS80 = new RRC_ssb_periodicityServingCell(4);
        public static final RRC_ssb_periodicityServingCell MS160 = new RRC_ssb_periodicityServingCell(5);
        public static final RRC_ssb_periodicityServingCell SPARE2 = new RRC_ssb_periodicityServingCell(6);
        public static final RRC_ssb_periodicityServingCell SPARE1 = new RRC_ssb_periodicityServingCell(7);
    
        private RRC_ssb_periodicityServingCell(long value) {
            super(value);
        }
    }

    public static class RRC_dmrs_TypeA_Position_2 extends AsnEnumerated {
        public static final RRC_dmrs_TypeA_Position_2 POS2 = new RRC_dmrs_TypeA_Position_2(0);
        public static final RRC_dmrs_TypeA_Position_2 POS3 = new RRC_dmrs_TypeA_Position_2(1);
    
        private RRC_dmrs_TypeA_Position_2(long value) {
            super(value);
        }
    }
}

