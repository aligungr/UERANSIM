/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__moreThanTwo__n1_n2 extends RRC_Choice {

    public RRC_BitString two_one_TypeI_SinglePanel_Restriction;
    public RRC_BitString two_two_TypeI_SinglePanel_Restriction;
    public RRC_BitString four_one_TypeI_SinglePanel_Restriction;
    public RRC_BitString three_two_TypeI_SinglePanel_Restriction;
    public RRC_BitString six_one_TypeI_SinglePanel_Restriction;
    public RRC_BitString four_two_TypeI_SinglePanel_Restriction;
    public RRC_BitString eight_one_TypeI_SinglePanel_Restriction;
    public RRC_BitString four_three_TypeI_SinglePanel_Restriction;
    public RRC_BitString six_two_TypeI_SinglePanel_Restriction;
    public RRC_BitString twelve_one_TypeI_SinglePanel_Restriction;
    public RRC_BitString four_four_TypeI_SinglePanel_Restriction;
    public RRC_BitString eight_two_TypeI_SinglePanel_Restriction;
    public RRC_BitString sixteen_one_TypeI_SinglePanel_Restriction;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "two-one-TypeI-SinglePanel-Restriction","two-two-TypeI-SinglePanel-Restriction","four-one-TypeI-SinglePanel-Restriction","three-two-TypeI-SinglePanel-Restriction","six-one-TypeI-SinglePanel-Restriction","four-two-TypeI-SinglePanel-Restriction","eight-one-TypeI-SinglePanel-Restriction","four-three-TypeI-SinglePanel-Restriction","six-two-TypeI-SinglePanel-Restriction","twelve-one-TypeI-SinglePanel-Restriction","four-four-TypeI-SinglePanel-Restriction","eight-two-TypeI-SinglePanel-Restriction","sixteen-one-TypeI-SinglePanel-Restriction" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "two_one_TypeI_SinglePanel_Restriction","two_two_TypeI_SinglePanel_Restriction","four_one_TypeI_SinglePanel_Restriction","three_two_TypeI_SinglePanel_Restriction","six_one_TypeI_SinglePanel_Restriction","four_two_TypeI_SinglePanel_Restriction","eight_one_TypeI_SinglePanel_Restriction","four_three_TypeI_SinglePanel_Restriction","six_two_TypeI_SinglePanel_Restriction","twelve_one_TypeI_SinglePanel_Restriction","four_four_TypeI_SinglePanel_Restriction","eight_two_TypeI_SinglePanel_Restriction","sixteen_one_TypeI_SinglePanel_Restriction" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
