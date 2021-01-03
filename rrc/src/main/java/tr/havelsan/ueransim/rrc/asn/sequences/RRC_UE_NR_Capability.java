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
import tr.havelsan.ueransim.rrc.asn.enums.RRC_AccessStratumRelease;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FeatureSetCombination;

public class RRC_UE_NR_Capability extends AsnSequence {
    public RRC_AccessStratumRelease accessStratumRelease; // mandatory
    public RRC_PDCP_Parameters pdcp_Parameters; // mandatory
    public RRC_RLC_Parameters rlc_Parameters; // optional
    public RRC_MAC_Parameters mac_Parameters; // optional
    public RRC_Phy_Parameters phy_Parameters; // mandatory
    public RRC_RF_Parameters rf_Parameters; // mandatory
    public RRC_MeasAndMobParameters measAndMobParameters; // optional
    public RRC_UE_NR_CapabilityAddXDD_Mode fdd_Add_UE_NR_Capabilities; // optional
    public RRC_UE_NR_CapabilityAddXDD_Mode tdd_Add_UE_NR_Capabilities; // optional
    public RRC_UE_NR_CapabilityAddFRX_Mode fr1_Add_UE_NR_Capabilities; // optional
    public RRC_UE_NR_CapabilityAddFRX_Mode fr2_Add_UE_NR_Capabilities; // optional
    public RRC_FeatureSets featureSets; // optional
    public RRC_featureSetCombinations_1 featureSetCombinations; // optional, SIZE(1..1024)
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_UE_NR_Capability_v1530 nonCriticalExtension; // optional

    // SIZE(1..1024)
    public static class RRC_featureSetCombinations_1 extends AsnSequenceOf<RRC_FeatureSetCombination> {
    }
}

