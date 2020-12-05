package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SRS_CC_SetIndex extends AsnSequence {
    public AsnInteger cc_SetIndex; // optional, VALUE(0..3)
    public AsnInteger cc_IndexInOneCC_Set; // optional, VALUE(0..7)
}

