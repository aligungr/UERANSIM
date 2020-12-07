/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;

public class RRC_BFR_CSIRS_Resource extends AsnSequence {
    public RRC_NZP_CSI_RS_ResourceId csi_RS; // mandatory
    public RRC_ra_OccasionList_1 ra_OccasionList; // optional, SIZE(1..64)
    public AsnInteger ra_PreambleIndex; // optional, VALUE(0..63)

    // SIZE(1..64)
    public static class RRC_ra_OccasionList_1 extends AsnSequenceOf<AsnInteger> {
    }
}

