/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_Parameters extends RRC_Sequence {

    public RRC_Phy_ParametersCommon phy_ParametersCommon;
    public RRC_Phy_ParametersXDD_Diff phy_ParametersXDD_Diff;
    public RRC_Phy_ParametersFRX_Diff phy_ParametersFRX_Diff;
    public RRC_Phy_ParametersFR1 phy_ParametersFR1;
    public RRC_Phy_ParametersFR2 phy_ParametersFR2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "phy-ParametersCommon","phy-ParametersXDD-Diff","phy-ParametersFRX-Diff","phy-ParametersFR1","phy-ParametersFR2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "phy_ParametersCommon","phy_ParametersXDD_Diff","phy_ParametersFRX_Diff","phy_ParametersFR1","phy_ParametersFR2" };
    }

    @Override
    public String getAsnName() {
        return "Phy-Parameters";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-Parameters";
    }

}
