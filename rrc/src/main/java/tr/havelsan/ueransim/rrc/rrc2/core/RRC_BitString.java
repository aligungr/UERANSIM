/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.rrc2.core;

import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_BitString extends RRC_Value {

    public BitString value;

    public RRC_BitString(BitString value) {
        this.value = value;
    }

    public RRC_BitString(OctetString octetString, int bitLength) {
        this(BitString.from(octetString, bitLength));
    }

    public RRC_BitString(OctetString octetString) {
        this(BitString.from(octetString));
    }

    public RRC_BitString(Octet[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public RRC_BitString(Octet[] octets) {
        this(BitString.from(octets));
    }

    public RRC_BitString(byte[] octets, int bitLength) {
        this(BitString.from(octets, bitLength));
    }

    public RRC_BitString(byte[] octets) {
        this(BitString.from(octets));
    }

    public RRC_BitString(String hex, int bitLength) {
        this(BitString.fromHex(hex, bitLength));
    }

    public RRC_BitString(String hex) {
        this(BitString.fromBits(hex));
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
