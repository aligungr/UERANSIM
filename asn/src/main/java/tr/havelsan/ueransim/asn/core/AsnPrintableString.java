/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.asn.core;

public class AsnPrintableString extends AsnValue {
    public String value;

    public AsnPrintableString() {
    }

    public AsnPrintableString(String value) {
        this.value = value;
    }
}
