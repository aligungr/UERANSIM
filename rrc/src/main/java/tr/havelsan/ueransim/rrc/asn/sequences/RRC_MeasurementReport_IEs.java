/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasurementReport_IEs extends AsnSequence {
    public RRC_MeasResults measResults; // mandatory
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_12 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_12 extends AsnSequence {
    }
}

