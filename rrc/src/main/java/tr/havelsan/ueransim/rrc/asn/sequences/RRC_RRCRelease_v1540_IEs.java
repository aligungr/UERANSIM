/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RejectWaitTime;

public class RRC_RRCRelease_v1540_IEs extends AsnSequence {
    public RRC_RejectWaitTime waitTime; // optional
    public RRC_nonCriticalExtension_24 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_24 extends AsnSequence {
    }
}

