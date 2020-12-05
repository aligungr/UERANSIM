package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnChoice;

public class RRC_S_NSSAI extends AsnChoice {
    public AsnBitString sst; // SIZE(8)
    public AsnBitString sst_SD; // SIZE(32)
}

