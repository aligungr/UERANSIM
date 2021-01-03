/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SearchSpaceId;

public class RRC_PDCCH_Config extends AsnSequence {
    public RRC_controlResourceSetToAddModList controlResourceSetToAddModList; // optional, SIZE(1..3)
    public RRC_controlResourceSetToReleaseList controlResourceSetToReleaseList; // optional, SIZE(1..3)
    public RRC_searchSpacesToAddModList searchSpacesToAddModList; // optional, SIZE(1..10)
    public RRC_searchSpacesToReleaseList searchSpacesToReleaseList; // optional, SIZE(1..10)
    public RRC_SetupRelease_DownlinkPreemption downlinkPreemption; // optional
    public RRC_SetupRelease_PUSCH_TPC_CommandConfig tpc_PUSCH; // optional
    public RRC_SetupRelease_PUCCH_TPC_CommandConfig tpc_PUCCH; // optional
    public RRC_SetupRelease_SRS_TPC_CommandConfig tpc_SRS; // optional

    // SIZE(1..10)
    public static class RRC_searchSpacesToAddModList extends AsnSequenceOf<RRC_SearchSpace> {
    }

    // SIZE(1..3)
    public static class RRC_controlResourceSetToReleaseList extends AsnSequenceOf<RRC_ControlResourceSetId> {
    }

    public static class RRC_SetupRelease_PUSCH_TPC_CommandConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PUSCH_TPC_CommandConfig setup;
    }

    // SIZE(1..3)
    public static class RRC_controlResourceSetToAddModList extends AsnSequenceOf<RRC_ControlResourceSet> {
    }

    public static class RRC_SetupRelease_SRS_TPC_CommandConfig extends AsnChoice {
        public AsnNull release;
        public RRC_SRS_TPC_CommandConfig setup;
    }

    public static class RRC_SetupRelease_DownlinkPreemption extends AsnChoice {
        public AsnNull release;
        public RRC_DownlinkPreemption setup;
    }

    public static class RRC_SetupRelease_PUCCH_TPC_CommandConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PUCCH_TPC_CommandConfig setup;
    }

    // SIZE(1..10)
    public static class RRC_searchSpacesToReleaseList extends AsnSequenceOf<RRC_SearchSpaceId> {
    }
}

