/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType codebookType;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "codebookType" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "codebookType" };
    }

    @Override
    public String getAsnName() {
        return "CodebookConfig";
    }

    @Override
    public String getXmlTagName() {
        return "CodebookConfig";
    }

}
