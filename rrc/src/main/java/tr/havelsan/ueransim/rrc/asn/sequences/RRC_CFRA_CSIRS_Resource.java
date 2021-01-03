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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_RS_Index;

public class RRC_CFRA_CSIRS_Resource extends AsnSequence {
    public RRC_CSI_RS_Index csi_RS; // mandatory
    public RRC_ra_OccasionList_2 ra_OccasionList; // mandatory, SIZE(1..64)
    public AsnInteger ra_PreambleIndex; // mandatory, VALUE(0..63)

    // SIZE(1..64)
    public static class RRC_ra_OccasionList_2 extends AsnSequenceOf<AsnInteger> {
    }
}

