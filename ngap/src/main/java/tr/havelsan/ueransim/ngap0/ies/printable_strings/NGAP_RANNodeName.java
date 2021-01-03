/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.printable_strings;

import tr.havelsan.ueransim.ngap0.core.NGAP_PrintableString;

public class NGAP_RANNodeName extends NGAP_PrintableString {

    public NGAP_RANNodeName(String value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RANNodeName";
    }

    @Override
    public String getXmlTagName() {
        return "RANNodeName";
    }
}
