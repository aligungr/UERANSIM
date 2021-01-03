/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortI_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ResumeCause;

public class RRC_RRCResumeRequest_IEs extends AsnSequence {
    public RRC_ShortI_RNTI_Value resumeIdentity; // mandatory
    public AsnBitString resumeMAC_I; // mandatory, SIZE(16)
    public RRC_ResumeCause resumeCause; // mandatory
    public AsnBitString spare; // mandatory, SIZE(1)
}

