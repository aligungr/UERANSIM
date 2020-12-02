/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType__type1__subType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type1 extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType__type1__subType subType;
    public RRC_Integer codebookMode;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "subType","codebookMode" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "subType","codebookMode" };
    }

}
