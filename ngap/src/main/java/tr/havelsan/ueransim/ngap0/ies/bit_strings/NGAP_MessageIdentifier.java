package tr.havelsan.ueransim.ngap0.ies.bit_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_MessageIdentifier extends NGAP_BitString {

    public NGAP_MessageIdentifier(BitString value) {
        super(value);
    }

    public NGAP_MessageIdentifier(OctetString octetString, int bitLength) {
        super(octetString, bitLength);
    }

    public NGAP_MessageIdentifier(OctetString octetString) {
        super(octetString);
    }

    public NGAP_MessageIdentifier(Octet[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_MessageIdentifier(Octet[] octets) {
        super(octets);
    }

    public NGAP_MessageIdentifier(byte[] octets, int bitLength) {
        super(octets, bitLength);
    }

    public NGAP_MessageIdentifier(byte[] octets) {
        super(octets);
    }

    public NGAP_MessageIdentifier(String hex, int bitLength) {
        super(hex, bitLength);
    }

    public NGAP_MessageIdentifier(String bits) {
        super(bits);
    }

    @Override
    public String getAsnName() {
        return "MessageIdentifier";
    }

    @Override
    public String getXmlTagName() {
        return "MessageIdentifier";
    }
}
