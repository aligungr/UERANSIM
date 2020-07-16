package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_SerialNumber extends NgapBitString {

    public NGAP_SerialNumber(BitString value) {
        super(value);
    }

    public NGAP_SerialNumber(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_SerialNumber(OctetString octetString) {
        super(octetString);
    }

    public NGAP_SerialNumber(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_SerialNumber(Octet[] octets) {
        super(octets);
    }

    public NGAP_SerialNumber(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_SerialNumber(byte[] octets) {
        super(octets);
    }

    public NGAP_SerialNumber(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_SerialNumber(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "SerialNumber";
    }

    @Override
    protected String getXmlTagName() {
        return "SerialNumber";
    }
}
