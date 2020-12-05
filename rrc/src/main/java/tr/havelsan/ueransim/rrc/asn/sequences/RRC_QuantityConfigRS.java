package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_QuantityConfigRS extends AsnSequence {
    public RRC_FilterConfig ssb_FilterConfig; // mandatory
    public RRC_FilterConfig csi_RS_FilterConfig; // mandatory
}

