/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
