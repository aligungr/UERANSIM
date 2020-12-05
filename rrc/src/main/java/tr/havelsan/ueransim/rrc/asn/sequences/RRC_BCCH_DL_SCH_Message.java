package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_BCCH_DL_SCH_MessageType;

public class RRC_BCCH_DL_SCH_Message extends AsnSequence {
    public RRC_BCCH_DL_SCH_MessageType message; // mandatory
}

