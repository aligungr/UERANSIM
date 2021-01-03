/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UE_NR_CapabilityAddXDD_Mode extends AsnSequence {
    public RRC_Phy_ParametersXDD_Diff phy_ParametersXDD_Diff; // optional
    public RRC_MAC_ParametersXDD_Diff mac_ParametersXDD_Diff; // optional
    public RRC_MeasAndMobParametersXDD_Diff measAndMobParametersXDD_Diff; // optional
}

