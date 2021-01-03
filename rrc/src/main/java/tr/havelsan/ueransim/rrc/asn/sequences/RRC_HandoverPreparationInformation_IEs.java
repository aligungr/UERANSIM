/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_CapabilityRAT_ContainerList;

public class RRC_HandoverPreparationInformation_IEs extends AsnSequence {
    public RRC_UE_CapabilityRAT_ContainerList ue_CapabilityRAT_List; // mandatory
    public RRC_AS_Config sourceConfig; // optional
    public RRC_RRM_Config rrm_Config; // optional
    public RRC_AS_Context as_Context; // optional
    public RRC_nonCriticalExtension_22 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_22 extends AsnSequence {
    }
}

