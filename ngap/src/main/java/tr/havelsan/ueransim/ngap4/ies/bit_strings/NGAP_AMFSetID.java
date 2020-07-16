package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_AMFSetID extends NgapBitString {

    public NGAP_AMFSetID(BitString value) {
        super(value);
    }

    public NGAP_AMFSetID(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_AMFSetID(OctetString octetString) {
        super(octetString);
    }

    public NGAP_AMFSetID(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFSetID(Octet[] octets) {
        super(octets);
    }

    public NGAP_AMFSetID(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_AMFSetID(byte[] octets) {
        super(octets);
    }

    public NGAP_AMFSetID(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_AMFSetID(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "AMFSetID";
    }

    @Override
    protected String getXmlTagName() {
        return "AMFSetID";
    }
}
