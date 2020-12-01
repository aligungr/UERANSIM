/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.core;

public class RRC_Boolean extends RRC_Value {

    public final boolean value;

    public RRC_Boolean(boolean value) {
        this.value = value;
    }

    @Override
    public String getAsnName() {
        return "BOOLEAN";
    }

    @Override
    public String getXmlTagName() {
        return "BOOLEAN";
    }
}
