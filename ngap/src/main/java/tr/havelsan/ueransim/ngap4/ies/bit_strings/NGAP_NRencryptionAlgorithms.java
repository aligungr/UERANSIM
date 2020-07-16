package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_NRencryptionAlgorithms extends NgapBitString {

    public NGAP_NRencryptionAlgorithms(BitString value) {
        super(value);
    }

    public NGAP_NRencryptionAlgorithms(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_NRencryptionAlgorithms(OctetString octetString) {
        super(octetString);
    }

    public NGAP_NRencryptionAlgorithms(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_NRencryptionAlgorithms(Octet[] octets) {
        super(octets);
    }

    public NGAP_NRencryptionAlgorithms(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_NRencryptionAlgorithms(byte[] octets) {
        super(octets);
    }

    public NGAP_NRencryptionAlgorithms(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_NRencryptionAlgorithms(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "NRencryptionAlgorithms";
    }

    @Override
    protected String getXmlTagName() {
        return "NRencryptionAlgorithms";
    }
}
