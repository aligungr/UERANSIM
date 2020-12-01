/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_AccessStratumRelease;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_NR_Capability__featureSetCombinations;

public class RRC_UE_NR_Capability extends RRC_Sequence {

    public RRC_AccessStratumRelease accessStratumRelease;
    public RRC_PDCP_Parameters pdcp_Parameters;
    public RRC_RLC_Parameters rlc_Parameters;
    public RRC_MAC_Parameters mac_Parameters;
    public RRC_Phy_Parameters phy_Parameters;
    public RRC_RF_Parameters rf_Parameters;
    public RRC_MeasAndMobParameters measAndMobParameters;
    public RRC_UE_NR_CapabilityAddXDD_Mode fdd_Add_UE_NR_Capabilities;
    public RRC_UE_NR_CapabilityAddXDD_Mode tdd_Add_UE_NR_Capabilities;
    public RRC_UE_NR_CapabilityAddFRX_Mode fr1_Add_UE_NR_Capabilities;
    public RRC_UE_NR_CapabilityAddFRX_Mode fr2_Add_UE_NR_Capabilities;
    public RRC_FeatureSets featureSets;
    public RRC_UE_NR_Capability__featureSetCombinations featureSetCombinations;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_UE_NR_Capability_v1530 nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "accessStratumRelease","pdcp-Parameters","rlc-Parameters","mac-Parameters","phy-Parameters","rf-Parameters","measAndMobParameters","fdd-Add-UE-NR-Capabilities","tdd-Add-UE-NR-Capabilities","fr1-Add-UE-NR-Capabilities","fr2-Add-UE-NR-Capabilities","featureSets","featureSetCombinations","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "accessStratumRelease","pdcp_Parameters","rlc_Parameters","mac_Parameters","phy_Parameters","rf_Parameters","measAndMobParameters","fdd_Add_UE_NR_Capabilities","tdd_Add_UE_NR_Capabilities","fr1_Add_UE_NR_Capabilities","fr2_Add_UE_NR_Capabilities","featureSets","featureSetCombinations","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-Capability";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-Capability";
    }

}
