package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_SD extends NGAP_OctetString {

    public NGAP_SD(OctetString value) {
        super(value);
    }

    public NGAP_SD(BitString value) {
        super(value);
    }

    public NGAP_SD(Octet[] octets) {
        super(octets);
    }

    public NGAP_SD(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_SD(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_SD(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "SD";
    }

    @Override
    public String getXmlTagName() {
        return "SD";
    }
}
