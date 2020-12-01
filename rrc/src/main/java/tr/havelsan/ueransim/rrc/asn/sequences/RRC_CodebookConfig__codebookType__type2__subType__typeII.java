/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType__type2__subType__typeII__n1_n2_codebookSubsetRestriction;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type2__subType__typeII extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType__type2__subType__typeII__n1_n2_codebookSubsetRestriction n1_n2_codebookSubsetRestriction;
    public RRC_BitString typeII_RI_Restriction;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "n1-n2-codebookSubsetRestriction","typeII-RI-Restriction" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "n1_n2_codebookSubsetRestriction","typeII_RI_Restriction" };
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
