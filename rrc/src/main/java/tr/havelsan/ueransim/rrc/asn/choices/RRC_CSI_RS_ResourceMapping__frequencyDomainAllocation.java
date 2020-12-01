/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_CSI_RS_ResourceMapping__frequencyDomainAllocation extends RRC_Choice {

    public RRC_BitString row1;
    public RRC_BitString row2;
    public RRC_BitString row4;
    public RRC_BitString other;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "row1","row2","row4","other" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "row1","row2","row4","other" };
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
