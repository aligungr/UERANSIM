/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;

public class RRC_UplinkConfig extends AsnSequence {
    public RRC_BWP_UplinkDedicated initialUplinkBWP; // optional
    public RRC_uplinkBWP_ToReleaseList uplinkBWP_ToReleaseList; // optional, SIZE(1..4)
    public RRC_uplinkBWP_ToAddModList uplinkBWP_ToAddModList; // optional, SIZE(1..4)
    public RRC_BWP_Id firstActiveUplinkBWP_Id; // optional
    public RRC_SetupRelease_PUSCH_ServingCellConfig pusch_ServingCellConfig; // optional
    public RRC_SetupRelease_SRS_CarrierSwitching carrierSwitching; // optional
    public RRC_ext1_29 ext1; // optional

    public static class RRC_ext1_29 extends AsnSequence {
        public AsnBoolean powerBoostPi2BPSK; // optional
        public RRC_uplinkChannelBW_PerSCS_List uplinkChannelBW_PerSCS_List; // optional, SIZE(1..5)
    
        // SIZE(1..5)
        public static class RRC_uplinkChannelBW_PerSCS_List extends AsnSequenceOf<RRC_SCS_SpecificCarrier> {
        }
    }

    // SIZE(1..4)
    public static class RRC_uplinkBWP_ToReleaseList extends AsnSequenceOf<RRC_BWP_Id> {
    }

    // SIZE(1..4)
    public static class RRC_uplinkBWP_ToAddModList extends AsnSequenceOf<RRC_BWP_Uplink> {
    }

    public static class RRC_SetupRelease_PUSCH_ServingCellConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PUSCH_ServingCellConfig setup;
    }

    public static class RRC_SetupRelease_SRS_CarrierSwitching extends AsnChoice {
        public AsnNull release;
        public RRC_SRS_CarrierSwitching setup;
    }
}

