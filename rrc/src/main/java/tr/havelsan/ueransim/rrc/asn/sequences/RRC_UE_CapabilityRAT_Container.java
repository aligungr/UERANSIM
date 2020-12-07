/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_RAT_Type;

public class RRC_UE_CapabilityRAT_Container extends AsnSequence {
    public RRC_RAT_Type rat_Type; // mandatory
    public AsnOctetString ue_CapabilityRAT_Container; // mandatory
}

