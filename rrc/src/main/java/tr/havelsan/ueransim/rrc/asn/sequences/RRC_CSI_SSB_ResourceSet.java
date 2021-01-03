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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_SSB_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_CSI_SSB_ResourceSet extends AsnSequence {
    public RRC_CSI_SSB_ResourceSetId csi_SSB_ResourceSetId; // mandatory
    public RRC_csi_SSB_ResourceList csi_SSB_ResourceList; // mandatory, SIZE(1..64)

    // SIZE(1..64)
    public static class RRC_csi_SSB_ResourceList extends AsnSequenceOf<RRC_SSB_Index> {
    }
}

