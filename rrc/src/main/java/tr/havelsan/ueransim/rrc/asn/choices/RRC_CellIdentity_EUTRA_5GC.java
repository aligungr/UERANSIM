package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;

public class RRC_CellIdentity_EUTRA_5GC extends AsnChoice {
    public AsnBitString cellIdentity_EUTRA; // SIZE(28)
    public AsnInteger cellId_index; // VALUE(1..12)
}

