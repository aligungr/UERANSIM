/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_MultiPanel__ng_n1_n2 extends RRC_Choice {

    public RRC_BitString two_two_one_TypeI_MultiPanel_Restriction;
    public RRC_BitString two_four_one_TypeI_MultiPanel_Restriction;
    public RRC_BitString four_two_one_TypeI_MultiPanel_Restriction;
    public RRC_BitString two_two_two_TypeI_MultiPanel_Restriction;
    public RRC_BitString two_eight_one_TypeI_MultiPanel_Restriction;
    public RRC_BitString four_four_one_TypeI_MultiPanel_Restriction;
    public RRC_BitString two_four_two_TypeI_MultiPanel_Restriction;
    public RRC_BitString four_two_two_TypeI_MultiPanel_Restriction;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "two-two-one-TypeI-MultiPanel-Restriction","two-four-one-TypeI-MultiPanel-Restriction","four-two-one-TypeI-MultiPanel-Restriction","two-two-two-TypeI-MultiPanel-Restriction","two-eight-one-TypeI-MultiPanel-Restriction","four-four-one-TypeI-MultiPanel-Restriction","two-four-two-TypeI-MultiPanel-Restriction","four-two-two-TypeI-MultiPanel-Restriction" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "two_two_one_TypeI_MultiPanel_Restriction","two_four_one_TypeI_MultiPanel_Restriction","four_two_one_TypeI_MultiPanel_Restriction","two_two_two_TypeI_MultiPanel_Restriction","two_eight_one_TypeI_MultiPanel_Restriction","four_four_one_TypeI_MultiPanel_Restriction","two_four_two_TypeI_MultiPanel_Restriction","four_two_two_TypeI_MultiPanel_Restriction" };
    }

}
