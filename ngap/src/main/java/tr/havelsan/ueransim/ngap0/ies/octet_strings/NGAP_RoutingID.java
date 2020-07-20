package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_RoutingID extends NGAP_OctetString {

    public NGAP_RoutingID(OctetString value) {
        super(value);
    }

    public NGAP_RoutingID(BitString value) {
        super(value);
    }

    public NGAP_RoutingID(Octet[] octets) {
        super(octets);
    }

    public NGAP_RoutingID(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_RoutingID(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_RoutingID(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "RoutingID";
    }

    @Override
    public String getXmlTagName() {
        return "RoutingID";
    }
}
