package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_DataCodingScheme extends NgapBitString {

    public NGAP_DataCodingScheme(BitString value) {
        super(value);
    }

    public NGAP_DataCodingScheme(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_DataCodingScheme(OctetString octetString) {
        super(octetString);
    }

    public NGAP_DataCodingScheme(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_DataCodingScheme(Octet[] octets) {
        super(octets);
    }

    public NGAP_DataCodingScheme(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_DataCodingScheme(byte[] octets) {
        super(octets);
    }

    public NGAP_DataCodingScheme(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_DataCodingScheme(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "DataCodingScheme";
    }

    @Override
    protected String getXmlTagName() {
        return "DataCodingScheme";
    }
}
