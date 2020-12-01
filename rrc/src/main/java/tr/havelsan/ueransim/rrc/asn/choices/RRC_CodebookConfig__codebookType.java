/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type1;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type2;

public class RRC_CodebookConfig__codebookType extends RRC_Choice {

    public RRC_CodebookConfig__codebookType__type1 type1;
    public RRC_CodebookConfig__codebookType__type2 type2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "type1","type2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "type1","type2" };
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
