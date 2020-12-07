/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UE_NR_CapabilityAddXDD_Mode extends AsnSequence {
    public RRC_Phy_ParametersXDD_Diff phy_ParametersXDD_Diff; // optional
    public RRC_MAC_ParametersXDD_Diff mac_ParametersXDD_Diff; // optional
    public RRC_MeasAndMobParametersXDD_Diff measAndMobParametersXDD_Diff; // optional
}

