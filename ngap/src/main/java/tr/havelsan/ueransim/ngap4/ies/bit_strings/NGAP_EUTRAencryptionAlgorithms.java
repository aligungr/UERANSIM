package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_EUTRAencryptionAlgorithms extends NgapBitString {

    public NGAP_EUTRAencryptionAlgorithms(BitString value) {
        super(value);
    }

    public NGAP_EUTRAencryptionAlgorithms(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(OctetString octetString) {
        super(octetString);
    }

    public NGAP_EUTRAencryptionAlgorithms(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(Octet[] octets) {
        super(octets);
    }

    public NGAP_EUTRAencryptionAlgorithms(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(byte[] octets) {
        super(octets);
    }

    public NGAP_EUTRAencryptionAlgorithms(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_EUTRAencryptionAlgorithms(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "EUTRAencryptionAlgorithms";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRAencryptionAlgorithms";
    }
}
