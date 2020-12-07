/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UE_NR_CapabilityAddFRX_Mode extends AsnSequence {
    public RRC_Phy_ParametersFRX_Diff phy_ParametersFRX_Diff; // optional
    public RRC_MeasAndMobParametersFRX_Diff measAndMobParametersFRX_Diff; // optional
}

