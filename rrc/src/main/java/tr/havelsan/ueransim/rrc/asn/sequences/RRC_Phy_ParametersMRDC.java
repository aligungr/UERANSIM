/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_Phy_ParametersMRDC__naics_Capability_List;

public class RRC_Phy_ParametersMRDC extends RRC_Sequence {

    public RRC_Phy_ParametersMRDC__naics_Capability_List naics_Capability_List;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "naics-Capability-List" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "naics_Capability_List" };
    }

    @Override
    public String getAsnName() {
        return "Phy-ParametersMRDC";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-ParametersMRDC";
    }

}
