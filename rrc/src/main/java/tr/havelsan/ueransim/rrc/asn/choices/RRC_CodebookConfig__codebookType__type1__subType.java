/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type1__subType__typeI_MultiPanel;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel;

public class RRC_CodebookConfig__codebookType__type1__subType extends RRC_Choice {

    public RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel typeI_SinglePanel;
    public RRC_CodebookConfig__codebookType__type1__subType__typeI_MultiPanel typeI_MultiPanel;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "typeI-SinglePanel","typeI-MultiPanel" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "typeI_SinglePanel","typeI_MultiPanel" };
    }

}
