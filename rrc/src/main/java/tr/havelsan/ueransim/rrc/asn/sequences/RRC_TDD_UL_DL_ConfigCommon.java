/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_TDD_UL_DL_ConfigCommon extends RRC_Sequence {

    public RRC_SubcarrierSpacing referenceSubcarrierSpacing;
    public RRC_TDD_UL_DL_Pattern pattern1;
    public RRC_TDD_UL_DL_Pattern pattern2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "referenceSubcarrierSpacing","pattern1","pattern2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "referenceSubcarrierSpacing","pattern1","pattern2" };
    }

    @Override
    public String getAsnName() {
        return "TDD-UL-DL-ConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "TDD-UL-DL-ConfigCommon";
    }

}
