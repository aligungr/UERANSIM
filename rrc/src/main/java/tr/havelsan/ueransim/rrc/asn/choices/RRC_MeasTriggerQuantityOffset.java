package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnInteger;

public class RRC_MeasTriggerQuantityOffset extends AsnChoice {
    public AsnInteger rsrp; // VALUE(-30..30)
    public AsnInteger rsrq; // VALUE(-30..30)
    public AsnInteger sinr; // VALUE(-30..30)
}

