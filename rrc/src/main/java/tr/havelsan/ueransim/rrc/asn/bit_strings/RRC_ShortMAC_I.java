/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_ShortMAC_I extends RRC_BitString {

    public RRC_ShortMAC_I(BitString value) {
        super(value);
    }

    public RRC_ShortMAC_I(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public RRC_ShortMAC_I(OctetString octetString) {
        super(octetString);
    }

    public RRC_ShortMAC_I(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_ShortMAC_I(Octet[] octets) {
        super(octets);
    }

    public RRC_ShortMAC_I(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_ShortMAC_I(byte[] octets) {
        super(octets);
    }

    public RRC_ShortMAC_I(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public RRC_ShortMAC_I(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
