/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSetCombination;

public class RRC_UE_MRDC_Capability extends AsnSequence {
    public RRC_MeasAndMobParametersMRDC measAndMobParametersMRDC; // optional
    public RRC_Phy_ParametersMRDC phy_ParametersMRDC_v1530; // optional
    public RRC_RF_ParametersMRDC rf_ParametersMRDC; // mandatory
    public RRC_GeneralParametersMRDC_XDD_Diff generalParametersMRDC; // optional
    public RRC_UE_MRDC_CapabilityAddXDD_Mode fdd_Add_UE_MRDC_Capabilities; // optional
    public RRC_UE_MRDC_CapabilityAddXDD_Mode tdd_Add_UE_MRDC_Capabilities; // optional
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr1_Add_UE_MRDC_Capabilities; // optional
    public RRC_UE_MRDC_CapabilityAddFRX_Mode fr2_Add_UE_MRDC_Capabilities; // optional
    public RRC_featureSetCombinations_2 featureSetCombinations; // optional, SIZE(1..1024)
    public RRC_PDCP_ParametersMRDC pdcp_ParametersMRDC_v1530; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_UE_MRDC_Capability_v1560 nonCriticalExtension; // optional

    // SIZE(1..1024)
    public static class RRC_featureSetCombinations_2 extends AsnSequenceOf<RRC_FeatureSetCombination> {
    }
}

