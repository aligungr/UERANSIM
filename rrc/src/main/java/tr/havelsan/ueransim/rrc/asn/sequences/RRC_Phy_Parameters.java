package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_Phy_Parameters extends AsnSequence {
    public RRC_Phy_ParametersCommon phy_ParametersCommon; // optional
    public RRC_Phy_ParametersXDD_Diff phy_ParametersXDD_Diff; // optional
    public RRC_Phy_ParametersFRX_Diff phy_ParametersFRX_Diff; // optional
    public RRC_Phy_ParametersFR1 phy_ParametersFR1; // optional
    public RRC_Phy_ParametersFR2 phy_ParametersFR2; // optional
}

