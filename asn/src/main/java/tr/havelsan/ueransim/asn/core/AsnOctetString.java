/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn.core;

import tr.havelsan.ueransim.utils.octets.OctetString;

public class AsnOctetString extends AsnValue {
    public OctetString value;

    public AsnOctetString() {

    }

    public AsnOctetString(OctetString value) {
        this.value = value;
    }
}
