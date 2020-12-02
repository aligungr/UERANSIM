/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType__type1__subType__typeI_MultiPanel__ng_n1_n2;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_MultiPanel extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType__type1__subType__typeI_MultiPanel__ng_n1_n2 ng_n1_n2;
    public RRC_BitString ri_Restriction;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ng-n1-n2","ri-Restriction" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ng_n1_n2","ri_Restriction" };
    }

}
