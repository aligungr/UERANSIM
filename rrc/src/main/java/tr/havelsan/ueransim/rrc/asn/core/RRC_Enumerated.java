/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.core;

public abstract class RRC_Enumerated extends RRC_Value {

    public final String sValue;

    protected RRC_Enumerated(String sValue) {
        this.sValue = sValue;
    }

    @Override
    public String getAsnName() {
        return "ENUMERATED";
    }

    @Override
    public String getXmlTagName() {
        return "ENUMERATED";
    }
}
