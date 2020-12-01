/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.bit_strings;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_TrackingAreaCode extends RRC_BitString {

    public RRC_TrackingAreaCode(BitString value) {
        super(value);
    }

    public RRC_TrackingAreaCode(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public RRC_TrackingAreaCode(OctetString octetString) {
        super(octetString);
    }

    public RRC_TrackingAreaCode(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_TrackingAreaCode(Octet[] octets) {
        super(octets);
    }

    public RRC_TrackingAreaCode(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public RRC_TrackingAreaCode(byte[] octets) {
        super(octets);
    }

    public RRC_TrackingAreaCode(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public RRC_TrackingAreaCode(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "TrackingAreaCode";
    }

    @Override
    public String getXmlTagName() {
        return "TrackingAreaCode";
    }

}
