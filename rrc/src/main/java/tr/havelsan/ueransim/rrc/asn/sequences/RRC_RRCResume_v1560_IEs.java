/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SK_Counter;

public class RRC_RRCResume_v1560_IEs extends AsnSequence {
    public AsnOctetString radioBearerConfig2; // optional, SIZE(0..MAX)
    public RRC_SK_Counter sk_Counter; // optional
    public RRC_nonCriticalExtension_20 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_20 extends AsnSequence {
    }
}

