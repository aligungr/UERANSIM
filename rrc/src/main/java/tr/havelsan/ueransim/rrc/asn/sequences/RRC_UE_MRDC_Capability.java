/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_UE_MRDC_Capability__featureSetCombinations;

public class RRC_UE_MRDC_Capability extends RRC_Sequence {

    public RRC_MeasAndMobParametersMRDC measAndMobParametersMRDC;
    public RRC_Phy_ParametersMRDC phy_ParametersMRDC_v1530;
    public RRC_RF_ParametersMRDC rf_ParametersMRDC;
    public RRC_GeneralParametersMRDC_XDD_Diff generalParametersMRDC;
    public RRC_UE_MRDC_CapabilityAddXDD_Mode fdd_Add_UE_MRDC_Capabilities;
    public RRC_UE_MRDC_CapabilityAddXDD_Mode tdd_Add_UE_MRDC_Capabilities;
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr1_Add_UE_MRDC_Capabilities;
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr2_Add_UE_MRDC_Capabilities;
    public RRC_UE_MRDC_Capability__featureSetCombinations featureSetCombinations;
    public RRC_PDCP_ParametersMRDC pdcp_ParametersMRDC_v1530;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_UE_MRDC_Capability_v1560 nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measAndMobParametersMRDC","phy-ParametersMRDC-v1530","rf-ParametersMRDC","generalParametersMRDC","fdd-Add-UE-MRDC-Capabilities","tdd-Add-UE-MRDC-Capabilities","fr1-Add-UE-MRDC-Capabilities","fr2-Add-UE-MRDC-Capabilities","featureSetCombinations","pdcp-ParametersMRDC-v1530","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measAndMobParametersMRDC","phy_ParametersMRDC_v1530","rf_ParametersMRDC","generalParametersMRDC","fdd_Add_UE_MRDC_Capabilities","tdd_Add_UE_MRDC_Capabilities","fr1_Add_UE_MRDC_Capabilities","fr2_Add_UE_MRDC_Capabilities","featureSetCombinations","pdcp_ParametersMRDC_v1530","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-MRDC-Capability";
    }

    @Override
    public String getXmlTagName() {
        return "UE-MRDC-Capability";
    }

}
