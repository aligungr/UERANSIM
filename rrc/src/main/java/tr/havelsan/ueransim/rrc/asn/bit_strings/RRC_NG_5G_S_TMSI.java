/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_NG_5G_S_TMSI extends RRC_BitString {

    public RRC_NG_5G_S_TMSI(BitString value) {
        super(value);
    }

    public RRC_NG_5G_S_TMSI(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public RRC_NG_5G_S_TMSI(OctetString octetString) {
        super(octetString);
    }

    public RRC_NG_5G_S_TMSI(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_NG_5G_S_TMSI(Octet[] octets) {
        super(octets);
    }

    public RRC_NG_5G_S_TMSI(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_NG_5G_S_TMSI(byte[] octets) {
        super(octets);
    }

    public RRC_NG_5G_S_TMSI(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public RRC_NG_5G_S_TMSI(String bits) {
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
