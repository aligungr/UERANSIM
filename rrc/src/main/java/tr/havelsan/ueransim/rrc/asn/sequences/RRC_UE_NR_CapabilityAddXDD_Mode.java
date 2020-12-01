/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_CapabilityAddXDD_Mode extends RRC_Sequence {

    public RRC_Phy_ParametersXDD_Diff phy_ParametersXDD_Diff;
    public RRC_MAC_ParametersXDD_Diff mac_ParametersXDD_Diff;
    public RRC_MeasAndMobParametersXDD_Diff measAndMobParametersXDD_Diff;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "phy-ParametersXDD-Diff","mac-ParametersXDD-Diff","measAndMobParametersXDD-Diff" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "phy_ParametersXDD_Diff","mac_ParametersXDD_Diff","measAndMobParametersXDD_Diff" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-CapabilityAddXDD-Mode";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-CapabilityAddXDD-Mode";
    }

}
