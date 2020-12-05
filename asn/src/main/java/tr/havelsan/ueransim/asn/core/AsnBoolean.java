/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn.core;

public class AsnBoolean extends AsnValue {
    public boolean value;

    public AsnBoolean() {
    }

    public AsnBoolean(boolean value) {
        this.value = value;
    }
}
