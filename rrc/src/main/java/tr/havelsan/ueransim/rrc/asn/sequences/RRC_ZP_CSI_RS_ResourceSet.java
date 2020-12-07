/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceSetId;

public class RRC_ZP_CSI_RS_ResourceSet extends AsnSequence {
    public RRC_ZP_CSI_RS_ResourceSetId zp_CSI_RS_ResourceSetId; // mandatory
    public RRC_zp_CSI_RS_ResourceIdList zp_CSI_RS_ResourceIdList; // mandatory, SIZE(1..16)

    // SIZE(1..16)
    public static class RRC_zp_CSI_RS_ResourceIdList extends AsnSequenceOf<RRC_ZP_CSI_RS_ResourceId> {
    }
}

