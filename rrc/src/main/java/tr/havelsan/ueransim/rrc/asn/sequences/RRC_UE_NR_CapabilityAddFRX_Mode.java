/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_CapabilityAddFRX_Mode extends RRC_Sequence {

    public RRC_Phy_ParametersFRX_Diff phy_ParametersFRX_Diff;
    public RRC_MeasAndMobParametersFRX_Diff measAndMobParametersFRX_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "phy-ParametersFRX-Diff","measAndMobParametersFRX-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "phy_ParametersFRX_Diff","measAndMobParametersFRX_Diff" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-CapabilityAddFRX-Mode";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-CapabilityAddFRX-Mode";
    }

}
