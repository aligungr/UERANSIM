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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasObjectId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TAG_Id;

public class RRC_ServingCellConfig extends AsnSequence {
    public RRC_TDD_UL_DL_ConfigDedicated tdd_UL_DL_ConfigurationDedicated; // optional
    public RRC_BWP_DownlinkDedicated initialDownlinkBWP; // optional
    public RRC_downlinkBWP_ToReleaseList downlinkBWP_ToReleaseList; // optional, SIZE(1..4)
    public RRC_downlinkBWP_ToAddModList downlinkBWP_ToAddModList; // optional, SIZE(1..4)
    public RRC_BWP_Id firstActiveDownlinkBWP_Id; // optional
    public RRC_bwp_InactivityTimer bwp_InactivityTimer; // optional
    public RRC_BWP_Id defaultDownlinkBWP_Id; // optional
    public RRC_UplinkConfig uplinkConfig; // optional
    public RRC_UplinkConfig supplementaryUplink; // optional
    public RRC_SetupRelease_PDCCH_ServingCellConfig pdcch_ServingCellConfig; // optional
    public RRC_SetupRelease_PDSCH_ServingCellConfig pdsch_ServingCellConfig; // optional
    public RRC_SetupRelease_CSI_MeasConfig csi_MeasConfig; // optional
    public RRC_sCellDeactivationTimer sCellDeactivationTimer; // optional
    public RRC_CrossCarrierSchedulingConfig crossCarrierSchedulingConfig; // optional
    public RRC_TAG_Id tag_Id; // mandatory
    public RRC_dummy_7 dummy; // optional
    public RRC_pathlossReferenceLinking pathlossReferenceLinking; // optional
    public RRC_MeasObjectId servingCellMO; // optional
    public RRC_ext1_26 ext1; // optional

    public static class RRC_ext1_26 extends AsnSequence {
        public RRC_SetupRelease_RateMatchPatternLTE_CRS lte_CRS_ToMatchAround; // optional
        public RRC_rateMatchPatternToAddModList_2 rateMatchPatternToAddModList; // optional, SIZE(1..4)
        public RRC_rateMatchPatternToReleaseList_1 rateMatchPatternToReleaseList; // optional, SIZE(1..4)
        public RRC_downlinkChannelBW_PerSCS_List downlinkChannelBW_PerSCS_List; // optional, SIZE(1..5)
    
        // SIZE(1..5)
        public static class RRC_downlinkChannelBW_PerSCS_List extends AsnSequenceOf<RRC_SCS_SpecificCarrier> {
        }
    
        // SIZE(1..4)
        public static class RRC_rateMatchPatternToAddModList_2 extends AsnSequenceOf<RRC_RateMatchPattern> {
        }
    
        // SIZE(1..4)
        public static class RRC_rateMatchPatternToReleaseList_1 extends AsnSequenceOf<RRC_RateMatchPatternId> {
        }
    }

    public static class RRC_pathlossReferenceLinking extends AsnEnumerated {
        public static final RRC_pathlossReferenceLinking SPCELL = new RRC_pathlossReferenceLinking(0);
        public static final RRC_pathlossReferenceLinking SCELL = new RRC_pathlossReferenceLinking(1);
    
        private RRC_pathlossReferenceLinking(long value) {
            super(value);
        }
    }

    public static class RRC_sCellDeactivationTimer extends AsnEnumerated {
        public static final RRC_sCellDeactivationTimer MS20 = new RRC_sCellDeactivationTimer(0);
        public static final RRC_sCellDeactivationTimer MS40 = new RRC_sCellDeactivationTimer(1);
        public static final RRC_sCellDeactivationTimer MS80 = new RRC_sCellDeactivationTimer(2);
        public static final RRC_sCellDeactivationTimer MS160 = new RRC_sCellDeactivationTimer(3);
        public static final RRC_sCellDeactivationTimer MS200 = new RRC_sCellDeactivationTimer(4);
        public static final RRC_sCellDeactivationTimer MS240 = new RRC_sCellDeactivationTimer(5);
        public static final RRC_sCellDeactivationTimer MS320 = new RRC_sCellDeactivationTimer(6);
        public static final RRC_sCellDeactivationTimer MS400 = new RRC_sCellDeactivationTimer(7);
        public static final RRC_sCellDeactivationTimer MS480 = new RRC_sCellDeactivationTimer(8);
        public static final RRC_sCellDeactivationTimer MS520 = new RRC_sCellDeactivationTimer(9);
        public static final RRC_sCellDeactivationTimer MS640 = new RRC_sCellDeactivationTimer(10);
        public static final RRC_sCellDeactivationTimer MS720 = new RRC_sCellDeactivationTimer(11);
        public static final RRC_sCellDeactivationTimer MS840 = new RRC_sCellDeactivationTimer(12);
        public static final RRC_sCellDeactivationTimer MS1280 = new RRC_sCellDeactivationTimer(13);
        public static final RRC_sCellDeactivationTimer SPARE2 = new RRC_sCellDeactivationTimer(14);
        public static final RRC_sCellDeactivationTimer SPARE1 = new RRC_sCellDeactivationTimer(15);
    
        private RRC_sCellDeactivationTimer(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_CSI_MeasConfig extends AsnChoice {
        public AsnNull release;
        public RRC_CSI_MeasConfig setup;
    }

    public static class RRC_dummy_7 extends AsnEnumerated {
        public static final RRC_dummy_7 ENABLED = new RRC_dummy_7(0);
    
        private RRC_dummy_7(long value) {
            super(value);
        }
    }

    // SIZE(1..4)
    public static class RRC_downlinkBWP_ToAddModList extends AsnSequenceOf<RRC_BWP_Downlink> {
    }

    // SIZE(1..4)
    public static class RRC_downlinkBWP_ToReleaseList extends AsnSequenceOf<RRC_BWP_Id> {
    }

    public static class RRC_bwp_InactivityTimer extends AsnEnumerated {
        public static final RRC_bwp_InactivityTimer MS2 = new RRC_bwp_InactivityTimer(0);
        public static final RRC_bwp_InactivityTimer MS3 = new RRC_bwp_InactivityTimer(1);
        public static final RRC_bwp_InactivityTimer MS4 = new RRC_bwp_InactivityTimer(2);
        public static final RRC_bwp_InactivityTimer MS5 = new RRC_bwp_InactivityTimer(3);
        public static final RRC_bwp_InactivityTimer MS6 = new RRC_bwp_InactivityTimer(4);
        public static final RRC_bwp_InactivityTimer MS8 = new RRC_bwp_InactivityTimer(5);
        public static final RRC_bwp_InactivityTimer MS10 = new RRC_bwp_InactivityTimer(6);
        public static final RRC_bwp_InactivityTimer MS20 = new RRC_bwp_InactivityTimer(7);
        public static final RRC_bwp_InactivityTimer MS30 = new RRC_bwp_InactivityTimer(8);
        public static final RRC_bwp_InactivityTimer MS40 = new RRC_bwp_InactivityTimer(9);
        public static final RRC_bwp_InactivityTimer MS50 = new RRC_bwp_InactivityTimer(10);
        public static final RRC_bwp_InactivityTimer MS60 = new RRC_bwp_InactivityTimer(11);
        public static final RRC_bwp_InactivityTimer MS80 = new RRC_bwp_InactivityTimer(12);
        public static final RRC_bwp_InactivityTimer MS100 = new RRC_bwp_InactivityTimer(13);
        public static final RRC_bwp_InactivityTimer MS200 = new RRC_bwp_InactivityTimer(14);
        public static final RRC_bwp_InactivityTimer MS300 = new RRC_bwp_InactivityTimer(15);
        public static final RRC_bwp_InactivityTimer MS500 = new RRC_bwp_InactivityTimer(16);
        public static final RRC_bwp_InactivityTimer MS750 = new RRC_bwp_InactivityTimer(17);
        public static final RRC_bwp_InactivityTimer MS1280 = new RRC_bwp_InactivityTimer(18);
        public static final RRC_bwp_InactivityTimer MS1920 = new RRC_bwp_InactivityTimer(19);
        public static final RRC_bwp_InactivityTimer MS2560 = new RRC_bwp_InactivityTimer(20);
        public static final RRC_bwp_InactivityTimer SPARE10 = new RRC_bwp_InactivityTimer(21);
        public static final RRC_bwp_InactivityTimer SPARE9 = new RRC_bwp_InactivityTimer(22);
        public static final RRC_bwp_InactivityTimer SPARE8 = new RRC_bwp_InactivityTimer(23);
        public static final RRC_bwp_InactivityTimer SPARE7 = new RRC_bwp_InactivityTimer(24);
        public static final RRC_bwp_InactivityTimer SPARE6 = new RRC_bwp_InactivityTimer(25);
        public static final RRC_bwp_InactivityTimer SPARE5 = new RRC_bwp_InactivityTimer(26);
        public static final RRC_bwp_InactivityTimer SPARE4 = new RRC_bwp_InactivityTimer(27);
        public static final RRC_bwp_InactivityTimer SPARE3 = new RRC_bwp_InactivityTimer(28);
        public static final RRC_bwp_InactivityTimer SPARE2 = new RRC_bwp_InactivityTimer(29);
        public static final RRC_bwp_InactivityTimer SPARE1 = new RRC_bwp_InactivityTimer(30);
    
        private RRC_bwp_InactivityTimer(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_PDCCH_ServingCellConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PDCCH_ServingCellConfig setup;
    }

    public static class RRC_SetupRelease_PDSCH_ServingCellConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PDSCH_ServingCellConfig setup;
    }
}

