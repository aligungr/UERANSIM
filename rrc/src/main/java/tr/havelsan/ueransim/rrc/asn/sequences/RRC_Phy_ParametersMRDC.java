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

public class RRC_Phy_ParametersMRDC extends AsnSequence {
    public RRC_naics_Capability_List naics_Capability_List; // optional, SIZE(1..8)

    // SIZE(1..8)
    public static class RRC_naics_Capability_List extends AsnSequenceOf<RRC_NAICS_Capability_Entry> {
    }
}

