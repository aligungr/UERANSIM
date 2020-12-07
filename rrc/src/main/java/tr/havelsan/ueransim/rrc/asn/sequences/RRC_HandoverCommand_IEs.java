/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_HandoverCommand_IEs extends AsnSequence {
    public AsnOctetString handoverCommandMessage; // mandatory, SIZE(0..MAX)
    public RRC_nonCriticalExtension_14 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_14 extends AsnSequence {
    }
}

