/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.core;

import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_OctetString extends NGAP_Value {

    public OctetString value;

    public NGAP_OctetString(OctetString value) {
        this.value = value;
    }

    public NGAP_OctetString(BitString value) {
        this(value.toOctetString());
    }

    public NGAP_OctetString(Octet[] octets) {
        this(new OctetString(octets));
    }

    public NGAP_OctetString(int[] octetInts) {
        this(new OctetString(octetInts));
    }

    public NGAP_OctetString(byte[] octetBytes) {
        this(new OctetString(octetBytes));
    }

    public NGAP_OctetString(String hex) {
        this(new OctetString(hex));
    }

    @Override
    public String getAsnName() {
        return "OCTET STRING";
    }

    @Override
    public String getXmlTagName() {
        return "OCTET_STRING";
    }
}
