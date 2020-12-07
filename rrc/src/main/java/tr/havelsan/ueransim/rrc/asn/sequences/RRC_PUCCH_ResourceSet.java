/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceSetId;

public class RRC_PUCCH_ResourceSet extends AsnSequence {
    public RRC_PUCCH_ResourceSetId pucch_ResourceSetId; // mandatory
    public RRC_resourceList resourceList; // mandatory, SIZE(1..32)
    public AsnInteger maxPayloadMinus1; // optional, VALUE(4..256)

    // SIZE(1..32)
    public static class RRC_resourceList extends AsnSequenceOf<RRC_PUCCH_ResourceId> {
    }
}

