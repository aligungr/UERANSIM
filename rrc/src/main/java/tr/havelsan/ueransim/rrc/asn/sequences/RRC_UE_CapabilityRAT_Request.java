package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_RAT_Type;

public class RRC_UE_CapabilityRAT_Request extends AsnSequence {
    public RRC_RAT_Type rat_Type; // mandatory
    public AsnOctetString capabilityRequestFilter; // optional
}

