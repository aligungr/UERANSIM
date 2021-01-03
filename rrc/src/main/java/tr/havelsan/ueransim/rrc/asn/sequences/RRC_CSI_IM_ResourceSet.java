/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_IM_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_IM_ResourceSetId;

public class RRC_CSI_IM_ResourceSet extends AsnSequence {
    public RRC_CSI_IM_ResourceSetId csi_IM_ResourceSetId; // mandatory
    public RRC_csi_IM_Resources csi_IM_Resources; // mandatory, SIZE(1..8)

    // SIZE(1..8)
    public static class RRC_csi_IM_Resources extends AsnSequenceOf<RRC_CSI_IM_ResourceId> {
    }
}

