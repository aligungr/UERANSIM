package tr.havelsan.ueransim.ngap4.ies.bit_strings;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NGAP_MaskedIMEISV extends NgapBitString {

    public NGAP_MaskedIMEISV(BitString value) {
        super(value);
    }

    public NGAP_MaskedIMEISV(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_MaskedIMEISV(OctetString octetString) {
        super(octetString);
    }

    public NGAP_MaskedIMEISV(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_MaskedIMEISV(Octet[] octets) {
        super(octets);
    }

    public NGAP_MaskedIMEISV(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_MaskedIMEISV(byte[] octets) {
        super(octets);
    }

    public NGAP_MaskedIMEISV(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_MaskedIMEISV(String hex) {
        super(hex);
    }

    @Override
    protected String getAsnName() {
        return "MaskedIMEISV";
    }

    @Override
    protected String getXmlTagName() {
        return "MaskedIMEISV";
    }
}
