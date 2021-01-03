/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.*;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_AperiodicTriggerStateList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_SemiPersistentOnPUSCH_TriggerStateList;

public class RRC_CSI_MeasConfig extends AsnSequence {
    public RRC_nzp_CSI_RS_ResourceToAddModList nzp_CSI_RS_ResourceToAddModList; // optional, SIZE(1..192)
    public RRC_nzp_CSI_RS_ResourceToReleaseList nzp_CSI_RS_ResourceToReleaseList; // optional, SIZE(1..192)
    public RRC_nzp_CSI_RS_ResourceSetToAddModList nzp_CSI_RS_ResourceSetToAddModList; // optional, SIZE(1..64)
    public RRC_nzp_CSI_RS_ResourceSetToReleaseList nzp_CSI_RS_ResourceSetToReleaseList; // optional, SIZE(1..64)
    public RRC_csi_IM_ResourceToAddModList csi_IM_ResourceToAddModList; // optional, SIZE(1..32)
    public RRC_csi_IM_ResourceToReleaseList csi_IM_ResourceToReleaseList; // optional, SIZE(1..32)
    public RRC_csi_IM_ResourceSetToAddModList csi_IM_ResourceSetToAddModList; // optional, SIZE(1..64)
    public RRC_csi_IM_ResourceSetToReleaseList csi_IM_ResourceSetToReleaseList; // optional, SIZE(1..64)
    public RRC_csi_SSB_ResourceSetToAddModList csi_SSB_ResourceSetToAddModList; // optional, SIZE(1..64)
    public RRC_csi_SSB_ResourceSetToReleaseList csi_SSB_ResourceSetToReleaseList; // optional, SIZE(1..64)
    public RRC_csi_ResourceConfigToAddModList csi_ResourceConfigToAddModList; // optional, SIZE(1..112)
    public RRC_csi_ResourceConfigToReleaseList csi_ResourceConfigToReleaseList; // optional, SIZE(1..112)
    public RRC_csi_ReportConfigToAddModList csi_ReportConfigToAddModList; // optional, SIZE(1..48)
    public RRC_csi_ReportConfigToReleaseList csi_ReportConfigToReleaseList; // optional, SIZE(1..48)
    public AsnInteger reportTriggerSize; // optional, VALUE(0..6)
    public RRC_SetupRelease_CSI_AperiodicTriggerStateList aperiodicTriggerStateList; // optional
    public RRC_SetupRelease_CSI_SemiPersistentOnPUSCH_TriggerStateList semiPersistentOnPUSCH_TriggerStateList; // optional

    public static class RRC_SetupRelease_CSI_SemiPersistentOnPUSCH_TriggerStateList extends AsnChoice {
        public AsnNull release;
        public RRC_CSI_SemiPersistentOnPUSCH_TriggerStateList setup;
    }

    public static class RRC_SetupRelease_CSI_AperiodicTriggerStateList extends AsnChoice {
        public AsnNull release;
        public RRC_CSI_AperiodicTriggerStateList setup;
    }

    // SIZE(1..64)
    public static class RRC_csi_SSB_ResourceSetToAddModList extends AsnSequenceOf<RRC_CSI_SSB_ResourceSet> {
    }

    // SIZE(1..112)
    public static class RRC_csi_ResourceConfigToAddModList extends AsnSequenceOf<RRC_CSI_ResourceConfig> {
    }

    // SIZE(1..32)
    public static class RRC_csi_IM_ResourceToReleaseList extends AsnSequenceOf<RRC_CSI_IM_ResourceId> {
    }

    // SIZE(1..48)
    public static class RRC_csi_ReportConfigToAddModList extends AsnSequenceOf<RRC_CSI_ReportConfig> {
    }

    // SIZE(1..192)
    public static class RRC_nzp_CSI_RS_ResourceToAddModList extends AsnSequenceOf<RRC_NZP_CSI_RS_Resource> {
    }

    // SIZE(1..64)
    public static class RRC_nzp_CSI_RS_ResourceSetToAddModList extends AsnSequenceOf<RRC_NZP_CSI_RS_ResourceSet> {
    }

    // SIZE(1..112)
    public static class RRC_csi_ResourceConfigToReleaseList extends AsnSequenceOf<RRC_CSI_ResourceConfigId> {
    }

    // SIZE(1..64)
    public static class RRC_csi_SSB_ResourceSetToReleaseList extends AsnSequenceOf<RRC_CSI_SSB_ResourceSetId> {
    }

    // SIZE(1..32)
    public static class RRC_csi_IM_ResourceToAddModList extends AsnSequenceOf<RRC_CSI_IM_Resource> {
    }

    // SIZE(1..192)
    public static class RRC_nzp_CSI_RS_ResourceToReleaseList extends AsnSequenceOf<RRC_NZP_CSI_RS_ResourceId> {
    }

    // SIZE(1..64)
    public static class RRC_nzp_CSI_RS_ResourceSetToReleaseList extends AsnSequenceOf<RRC_NZP_CSI_RS_ResourceSetId> {
    }

    // SIZE(1..64)
    public static class RRC_csi_IM_ResourceSetToAddModList extends AsnSequenceOf<RRC_CSI_IM_ResourceSet> {
    }

    // SIZE(1..64)
    public static class RRC_csi_IM_ResourceSetToReleaseList extends AsnSequenceOf<RRC_CSI_IM_ResourceSetId> {
    }

    // SIZE(1..48)
    public static class RRC_csi_ReportConfigToReleaseList extends AsnSequenceOf<RRC_CSI_ReportConfigId> {
    }
}

