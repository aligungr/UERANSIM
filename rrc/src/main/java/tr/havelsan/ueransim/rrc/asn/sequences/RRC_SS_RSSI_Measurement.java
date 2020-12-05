package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SS_RSSI_Measurement extends AsnSequence {
    public AsnBitString measurementSlots; // mandatory, SIZE(1..80)
    public AsnInteger endSymbol; // mandatory, VALUE(0..3)
}

