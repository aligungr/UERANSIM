/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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
