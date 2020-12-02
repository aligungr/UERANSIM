/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_CodebookConfig__codebookType__type2__subType__typeII__n1_n2_codebookSubsetRestriction extends RRC_Choice {

    public RRC_BitString two_one;
    public RRC_BitString two_two;
    public RRC_BitString four_one;
    public RRC_BitString three_two;
    public RRC_BitString six_one;
    public RRC_BitString four_two;
    public RRC_BitString eight_one;
    public RRC_BitString four_three;
    public RRC_BitString six_two;
    public RRC_BitString twelve_one;
    public RRC_BitString four_four;
    public RRC_BitString eight_two;
    public RRC_BitString sixteen_one;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "two-one","two-two","four-one","three-two","six-one","four-two","eight-one","four-three","six-two","twelve-one","four-four","eight-two","sixteen-one" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "two_one","two_two","four_one","three_two","six_one","four_two","eight_one","four_three","six_two","twelve_one","four_four","eight_two","sixteen_one" };
    }

}
