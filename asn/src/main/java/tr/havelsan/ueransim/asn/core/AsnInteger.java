/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn.core;

public class AsnInteger extends AsnValue {
    public long value;

    public AsnInteger() {
    }

    public AsnInteger(long value) {
        this.value = value;
    }
}
