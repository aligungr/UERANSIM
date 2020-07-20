package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PortNumber extends NGAP_OctetString {

    public NGAP_PortNumber(OctetString value) {
        super(value);
    }

    public NGAP_PortNumber(BitString value) {
        super(value);
    }

    public NGAP_PortNumber(Octet[] octets) {
        super(octets);
    }

    public NGAP_PortNumber(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_PortNumber(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_PortNumber(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "PortNumber";
    }

    @Override
    public String getXmlTagName() {
        return "PortNumber";
    }
}
