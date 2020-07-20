package tr.havelsan.ueransim.ngap0.ies.bit_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_MaskedIMEISV extends NGAP_BitString {

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

    public NGAP_MaskedIMEISV(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "MaskedIMEISV";
    }

    @Override
    public String getXmlTagName() {
        return "MaskedIMEISV";
    }
}
