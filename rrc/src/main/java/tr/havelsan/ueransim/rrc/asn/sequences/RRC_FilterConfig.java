package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_FilterCoefficient;

public class RRC_FilterConfig extends AsnSequence {
    public RRC_FilterCoefficient filterCoefficientRSRP; // optional
    public RRC_FilterCoefficient filterCoefficientRSRQ; // optional
    public RRC_FilterCoefficient filterCoefficientRS_SINR; // optional
}

