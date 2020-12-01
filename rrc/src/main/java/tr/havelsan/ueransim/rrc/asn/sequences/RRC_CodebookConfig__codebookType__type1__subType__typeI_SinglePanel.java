/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType__type1__subType__typeI_SinglePanel__nrOfAntennaPorts nrOfAntennaPorts;
    public RRC_BitString typeI_SinglePanel_ri_Restriction;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nrOfAntennaPorts","typeI-SinglePanel-ri-Restriction" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nrOfAntennaPorts","typeI_SinglePanel_ri_Restriction" };
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
