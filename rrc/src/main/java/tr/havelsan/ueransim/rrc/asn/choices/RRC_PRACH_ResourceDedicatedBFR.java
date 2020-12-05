package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BFR_CSIRS_Resource;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BFR_SSB_Resource;

public class RRC_PRACH_ResourceDedicatedBFR extends AsnChoice {
    public RRC_BFR_SSB_Resource ssb;
    public RRC_BFR_CSIRS_Resource csi_RS;
}

