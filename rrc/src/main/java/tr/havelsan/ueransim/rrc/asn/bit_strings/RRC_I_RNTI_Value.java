/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_I_RNTI_Value extends RRC_BitString {

    public RRC_I_RNTI_Value(BitString value) {
        super(value);
    }

    public RRC_I_RNTI_Value(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public RRC_I_RNTI_Value(OctetString octetString) {
        super(octetString);
    }

    public RRC_I_RNTI_Value(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_I_RNTI_Value(Octet[] octets) {
        super(octets);
    }

    public RRC_I_RNTI_Value(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_I_RNTI_Value(byte[] octets) {
        super(octets);
    }

    public RRC_I_RNTI_Value(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public RRC_I_RNTI_Value(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "I-RNTI-Value";
    }

    @Override
    public String getXmlTagName() {
        return "I-RNTI-Value";
    }

}
