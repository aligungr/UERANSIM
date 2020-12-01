/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CodebookParameters extends RRC_Sequence {

    public RRC_CodebookParameters__type1 type1;
    public RRC_CodebookParameters__type2 type2;
    public RRC_CodebookParameters__type2_PortSelection type2_PortSelection;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "type1","type2","type2-PortSelection" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "type1","type2","type2_PortSelection" };
    }

    @Override
    public String getAsnName() {
        return "CodebookParameters";
    }

    @Override
    public String getXmlTagName() {
        return "CodebookParameters";
    }

}
