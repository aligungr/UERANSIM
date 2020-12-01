/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.*;

public class RRC_SystemInformation_IEs__sib_TypeAndInfo__Member extends RRC_Choice {

    public RRC_SIB2 sib2;
    public RRC_SIB3 sib3;
    public RRC_SIB4 sib4;
    public RRC_SIB5 sib5;
    public RRC_SIB6 sib6;
    public RRC_SIB7 sib7;
    public RRC_SIB8 sib8;
    public RRC_SIB9 sib9;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sib2","sib3","sib4","sib5","sib6","sib7","sib8","sib9" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sib2","sib3","sib4","sib5","sib6","sib7","sib8","sib9" };
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
