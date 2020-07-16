package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_EUTRAintegrityProtectionAlgorithms extends NgapBitString {

    public NGAP_EUTRAintegrityProtectionAlgorithms(BitString value) {
        super(value);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(OctetString octetString) {
        super(octetString);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(Octet[] octets) {
        super(octets);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(byte[] octets) {
        super(octets);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_EUTRAintegrityProtectionAlgorithms(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "EUTRAintegrityProtectionAlgorithms";
    }

    @Override
    protected String getXmlTagName() {
        return "EUTRAintegrityProtectionAlgorithms";
    }
}
