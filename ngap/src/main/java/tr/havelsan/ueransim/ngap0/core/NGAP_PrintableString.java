/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.core;

public class NGAP_PrintableString extends NGAP_Value {

    public String value;

    public NGAP_PrintableString(String value) {
        this.value = value;
    }

    @Override
    public String getAsnName() {
        return "PrintableString";
    }

    @Override
    public String getXmlTagName() {
        return "PrintableString";
    }
}
