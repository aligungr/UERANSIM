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

public class RRC_UE_NR_Capability_v1560 extends AsnSequence {
    public RRC_NRDC_Parameters nrdc_Parameters; // optional
    public AsnOctetString receivedFilters; // optional, SIZE(0..MAX)
    public RRC_nonCriticalExtension_21 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_21 extends AsnSequence {
    }
}

