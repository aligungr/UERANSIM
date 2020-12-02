/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__moreThanTwo;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__two;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts extends RRC_Choice {

    public RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__two two;
    public RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts__moreThanTwo moreThanTwo;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "two","moreThanTwo" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "two","moreThanTwo" };
    }

}
