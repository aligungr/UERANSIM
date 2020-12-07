/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SIB9 extends AsnSequence {
    public RRC_timeInfo timeInfo; // optional
    public AsnOctetString lateNonCriticalExtension; // optional

    public static class RRC_timeInfo extends AsnSequence {
        public AsnInteger timeInfoUTC; // mandatory, VALUE(0..549755813887)
        public AsnBitString dayLightSavingTime; // optional, SIZE(2)
        public AsnInteger leapSeconds; // optional, VALUE(-127..128)
        public AsnInteger localTimeOffset; // optional, VALUE(-63..64)
    }
}

