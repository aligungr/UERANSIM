package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_DL_CCCH_MessageType;

public class RRC_DL_CCCH_Message extends AsnSequence {
    public RRC_DL_CCCH_MessageType message; // mandatory
}

