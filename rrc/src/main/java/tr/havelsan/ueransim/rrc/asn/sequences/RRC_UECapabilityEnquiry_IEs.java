/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_CapabilityRAT_RequestList;

public class RRC_UECapabilityEnquiry_IEs extends AsnSequence {
    public RRC_UE_CapabilityRAT_RequestList ue_CapabilityRAT_RequestList; // mandatory
    public AsnOctetString lateNonCriticalExtension; // optional
    public AsnOctetString ue_CapabilityEnquiryExt; // optional, SIZE(0..MAX)
}

