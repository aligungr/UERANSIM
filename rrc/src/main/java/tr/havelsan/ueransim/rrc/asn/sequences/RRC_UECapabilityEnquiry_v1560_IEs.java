/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UECapabilityEnquiry_v1560_IEs extends AsnSequence {
    public RRC_UE_CapabilityRequestFilterCommon capabilityRequestFilterCommon; // optional
    public RRC_nonCriticalExtension_32 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_32 extends AsnSequence {
    }
}

