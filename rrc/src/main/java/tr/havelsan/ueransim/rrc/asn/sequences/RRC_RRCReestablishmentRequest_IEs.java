package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_ReestablishmentCause;

public class RRC_RRCReestablishmentRequest_IEs extends AsnSequence {
    public RRC_ReestabUE_Identity ue_Identity; // mandatory
    public RRC_ReestablishmentCause reestablishmentCause; // mandatory
    public AsnBitString spare; // mandatory, SIZE(1)
}

