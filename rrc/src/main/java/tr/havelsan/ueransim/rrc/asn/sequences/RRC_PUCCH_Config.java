/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUCCH_FormatConfig;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_SpatialRelationInfoId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestResourceId;

public class RRC_PUCCH_Config extends AsnSequence {
    public RRC_resourceSetToAddModList resourceSetToAddModList; // optional, SIZE(1..4)
    public RRC_resourceSetToReleaseList resourceSetToReleaseList; // optional, SIZE(1..4)
    public RRC_resourceToAddModList resourceToAddModList; // optional, SIZE(1..128)
    public RRC_resourceToReleaseList resourceToReleaseList; // optional, SIZE(1..128)
    public RRC_SetupRelease_PUCCH_FormatConfig format1; // optional
    public RRC_SetupRelease_PUCCH_FormatConfig format2; // optional
    public RRC_SetupRelease_PUCCH_FormatConfig format3; // optional
    public RRC_SetupRelease_PUCCH_FormatConfig format4; // optional
    public RRC_schedulingRequestResourceToAddModList schedulingRequestResourceToAddModList; // optional, SIZE(1..8)
    public RRC_schedulingRequestResourceToReleaseList schedulingRequestResourceToReleaseList; // optional, SIZE(1..8)
    public RRC_multi_CSI_PUCCH_ResourceList multi_CSI_PUCCH_ResourceList; // optional, SIZE(1..2)
    public RRC_dl_DataToUL_ACK dl_DataToUL_ACK; // optional, SIZE(1..8)
    public RRC_spatialRelationInfoToAddModList spatialRelationInfoToAddModList; // optional, SIZE(1..8)
    public RRC_spatialRelationInfoToReleaseList spatialRelationInfoToReleaseList; // optional, SIZE(1..8)
    public RRC_PUCCH_PowerControl pucch_PowerControl; // optional

    // SIZE(1..8)
    public static class RRC_schedulingRequestResourceToReleaseList extends AsnSequenceOf<RRC_SchedulingRequestResourceId> {
    }

    // SIZE(1..128)
    public static class RRC_resourceToAddModList extends AsnSequenceOf<RRC_PUCCH_Resource> {
    }

    // SIZE(1..4)
    public static class RRC_resourceSetToReleaseList extends AsnSequenceOf<RRC_PUCCH_ResourceSetId> {
    }

    // SIZE(1..128)
    public static class RRC_resourceToReleaseList extends AsnSequenceOf<RRC_PUCCH_ResourceId> {
    }

    // SIZE(1..8)
    public static class RRC_schedulingRequestResourceToAddModList extends AsnSequenceOf<RRC_SchedulingRequestResourceConfig> {
    }

    // SIZE(1..2)
    public static class RRC_multi_CSI_PUCCH_ResourceList extends AsnSequenceOf<RRC_PUCCH_ResourceId> {
    }

    // SIZE(1..8)
    public static class RRC_spatialRelationInfoToAddModList extends AsnSequenceOf<RRC_PUCCH_SpatialRelationInfo> {
    }

    // SIZE(1..8)
    public static class RRC_spatialRelationInfoToReleaseList extends AsnSequenceOf<RRC_PUCCH_SpatialRelationInfoId> {
    }

    // SIZE(1..4)
    public static class RRC_resourceSetToAddModList extends AsnSequenceOf<RRC_PUCCH_ResourceSet> {
    }

    // SIZE(1..8)
    public static class RRC_dl_DataToUL_ACK extends AsnSequenceOf<AsnInteger> {
    }
}

