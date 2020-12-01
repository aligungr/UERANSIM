/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CodebookConfig__codebookType__type2__subType;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookConfig__codebookType__type2 extends RRC_Sequence {

    public RRC_CodebookConfig__codebookType__type2__subType subType;
    public RRC_Integer phaseAlphabetSize;
    public RRC_Boolean subbandAmplitude;
    public RRC_Integer numberOfBeams;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "subType","phaseAlphabetSize","subbandAmplitude","numberOfBeams" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "subType","phaseAlphabetSize","subbandAmplitude","numberOfBeams" };
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
