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

public class RRC_UE_MRDC_Capability_v1560 extends AsnSequence {
    public AsnOctetString receivedFilters; // optional, SIZE(0..MAX)
    public RRC_MeasAndMobParametersMRDC_v1560 measAndMobParametersMRDC_v1560; // optional
    public RRC_UE_MRDC_CapabilityAddXDD_Mode_v1560 fdd_Add_UE_MRDC_Capabilities_v1560; // optional
    public RRC_UE_MRDC_CapabilityAddXDD_Mode_v1560 tdd_Add_UE_MRDC_Capabilities_v1560; // optional
    public RRC_nonCriticalExtension_3 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_3 extends AsnSequence {
    }
}

