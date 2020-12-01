/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_RateMatchPattern__patternType__bitmaps__periodicityAndPattern extends RRC_Choice {

    public RRC_BitString n2;
    public RRC_BitString n4;
    public RRC_BitString n5;
    public RRC_BitString n8;
    public RRC_BitString n10;
    public RRC_BitString n20;
    public RRC_BitString n40;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "n2","n4","n5","n8","n10","n20","n40" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "n2","n4","n5","n8","n10","n20","n40" };
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
