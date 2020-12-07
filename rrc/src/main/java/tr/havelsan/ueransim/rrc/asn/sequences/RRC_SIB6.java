/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SIB6 extends AsnSequence {
    public AsnBitString messageIdentifier; // mandatory, SIZE(16)
    public AsnBitString serialNumber; // mandatory, SIZE(16)
    public AsnOctetString warningType; // mandatory, SIZE(2)
    public AsnOctetString lateNonCriticalExtension; // optional
}

