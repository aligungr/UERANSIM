package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_SecurityKey extends NgapBitString {

    public NGAP_SecurityKey(BitString value) {
        super(value);
    }

    public NGAP_SecurityKey(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_SecurityKey(OctetString octetString) {
        super(octetString);
    }

    public NGAP_SecurityKey(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_SecurityKey(Octet[] octets) {
        super(octets);
    }

    public NGAP_SecurityKey(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_SecurityKey(byte[] octets) {
        super(octets);
    }

    public NGAP_SecurityKey(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_SecurityKey(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "SecurityKey";
    }

    @Override
    protected String getXmlTagName() {
        return "SecurityKey";
    }
}
