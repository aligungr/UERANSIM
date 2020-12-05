/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn.core;

public class AsnEnumerated extends AsnValue {
    public final long intValue;

    protected AsnEnumerated(long intValue) {
        this.intValue = intValue;
    }
}
