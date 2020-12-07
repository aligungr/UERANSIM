/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NextHopChainingCount;

public class RRC_RRCReestablishment_IEs extends AsnSequence {
    public RRC_NextHopChainingCount nextHopChainingCount; // mandatory
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_18 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_18 extends AsnSequence {
    }
}

