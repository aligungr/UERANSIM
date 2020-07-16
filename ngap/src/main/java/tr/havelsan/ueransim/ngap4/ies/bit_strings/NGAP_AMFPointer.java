package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_AMFPointer extends NgapBitString {

    public NGAP_AMFPointer(BitString value) {
        super(value);
    }

    public NGAP_AMFPointer(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_AMFPointer(OctetString octetString) {
        super(octetString);
    }

    public NGAP_AMFPointer(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFPointer(Octet[] octets) {
        super(octets);
    }

    public NGAP_AMFPointer(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFPointer(byte[] octets) {
        super(octets);
    }

    public NGAP_AMFPointer(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_AMFPointer(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "AMFPointer";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFPointer";
    }
}
