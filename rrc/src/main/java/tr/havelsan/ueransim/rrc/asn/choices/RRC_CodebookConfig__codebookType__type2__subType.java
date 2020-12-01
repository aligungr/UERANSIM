/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type2__subType__typeII;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CodebookConfig__codebookType__type2__subType__typeII_PortSelection;

public class RRC_CodebookConfig__codebookType__type2__subType extends RRC_Choice {

    public RRC_CodebookConfig__codebookType__type2__subType__typeII typeII;
    public RRC_CodebookConfig__codebookType__type2__subType__typeII_PortSelection typeII_PortSelection;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "typeII","typeII-PortSelection" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "typeII","typeII_PortSelection" };
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
