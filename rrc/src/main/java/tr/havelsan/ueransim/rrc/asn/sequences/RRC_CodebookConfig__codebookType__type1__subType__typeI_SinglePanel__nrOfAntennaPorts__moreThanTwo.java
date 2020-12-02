/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__moreThanTwo__n1_n2;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__moreThanTwo extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__moreThanTwo__n1_n2 n1_n2;
    public RRC_BitString typeI_SinglePanel_codebookSubsetRestriction_i2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "n1-n2","typeI-SinglePanel-codebookSubsetRestriction-i2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "n1_n2","typeI_SinglePanel_codebookSubsetRestriction_i2" };
    }

}
