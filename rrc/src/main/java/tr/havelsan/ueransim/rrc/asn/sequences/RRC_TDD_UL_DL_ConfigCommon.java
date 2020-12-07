/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_TDD_UL_DL_ConfigCommon extends AsnSequence {
    public RRC_SubcarrierSpacing referenceSubcarrierSpacing; // mandatory
    public RRC_TDD_UL_DL_Pattern pattern1; // mandatory
    public RRC_TDD_UL_DL_Pattern pattern2; // optional
}

