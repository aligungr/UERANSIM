package tr.havelsan.ueransim.ngap0.ies.bit_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_EUTRACellIdentity extends NGAP_BitString {

    public NGAP_EUTRACellIdentity(BitString value) {
        super(value);
    }

    public NGAP_EUTRACellIdentity(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_EUTRACellIdentity(OctetString octetString) {
        super(octetString);
    }

    public NGAP_EUTRACellIdentity(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRACellIdentity(Octet[] octets) {
        super(octets);
    }

    public NGAP_EUTRACellIdentity(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_EUTRACellIdentity(byte[] octets) {
        super(octets);
    }

    public NGAP_EUTRACellIdentity(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_EUTRACellIdentity(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "EUTRACellIdentity";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRACellIdentity";
    }
}
