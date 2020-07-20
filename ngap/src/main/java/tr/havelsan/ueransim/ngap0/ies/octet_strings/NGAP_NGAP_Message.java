package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NGAP_Message extends NGAP_OctetString {

    public NGAP_NGAP_Message(OctetString value) {
        super(value);
    }

    public NGAP_NGAP_Message(BitString value) {
        super(value);
    }

    public NGAP_NGAP_Message(Octet[] octets) {
        super(octets);
    }

    public NGAP_NGAP_Message(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_NGAP_Message(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_NGAP_Message(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "NGAP-Message";
    }

    @Override
    public String getXmlTagName() {
        return "NGAP-Message";
    }
}
