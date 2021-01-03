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

public class NGAP_BitString extends NGAP_Value {

    public BitString value;

    public NGAP_BitString(BitString value) {
        this.value = value;
    }

    public NGAP_BitString(OctetString octetString, int bitLength) {
        this(BitString.from(octetString, bitLength));
    }

    public NGAP_BitString(OctetString octetString) {
        this(BitString.from(octetString));
    }

    public NGAP_BitString(Octet[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public NGAP_BitString(Octet[] octets) {
        this(BitString.from(octets));
    }

    public NGAP_BitString(byte[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public NGAP_BitString(byte[] octets) {
        this(BitString.from(octets));
    }

    public NGAP_BitString(String hex, int bitLength) {
        this(BitString.fromHex(hex, bitLength));
    }

    public NGAP_BitString(String bits) {
        this(BitString.fromBits(bits));
    }

    @Override
    public String getAsnName() {
        return "BIT STRING";
    }

    @Override
    public String getXmlTagName() {
        return "BIT_STRING";
    }
}
