package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_TAC extends NGAP_OctetString {

    public NGAP_TAC(OctetString value) {
        super(value);
    }

    public NGAP_TAC(BitString value) {
        super(value);
    }

    public NGAP_TAC(Octet[] octets) {
        super(octets);
    }

    public NGAP_TAC(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_TAC(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_TAC(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "TAC";
    }

    @Override
    public String getXmlTagName() {
        return "TAC";
    }
}
