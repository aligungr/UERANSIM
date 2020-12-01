/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.octet_strings;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_DedicatedNAS_Message extends RRC_OctetString {

    public RRC_DedicatedNAS_Message(OctetString value) {
        super(value);
    }

    public RRC_DedicatedNAS_Message(BitString value) {
        super(value);
    }

    public RRC_DedicatedNAS_Message(Octet[] octets) {
        super(octets);
    }

    public RRC_DedicatedNAS_Message(int[] octetInts) {
        super(octetInts);
    }

    public RRC_DedicatedNAS_Message(byte[] octetBytes) {
        super(octetBytes);
    }

    public RRC_DedicatedNAS_Message(String hex) {
        super(hex);
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
