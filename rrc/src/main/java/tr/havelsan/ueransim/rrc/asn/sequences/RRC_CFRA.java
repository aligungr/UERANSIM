/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CFRA__resources;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CFRA extends RRC_Sequence {

    public RRC_CFRA__occasions occasions;
    public RRC_CFRA__resources resources;
    public RRC_CFRA__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "occasions","resources","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "occasions","resources","ext1" };
    }

    @Override
    public String getAsnName() {
        return "CFRA";
    }

    @Override
    public String getXmlTagName() {
        return "CFRA";
    }

}
