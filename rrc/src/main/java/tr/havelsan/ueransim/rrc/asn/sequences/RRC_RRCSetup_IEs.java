/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_RRCSetup_IEs extends AsnSequence {
    public RRC_RadioBearerConfig radioBearerConfig; // mandatory
    public AsnOctetString masterCellGroup; // mandatory, SIZE(0..MAX)
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_23 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_23 extends AsnSequence {
    }
}

