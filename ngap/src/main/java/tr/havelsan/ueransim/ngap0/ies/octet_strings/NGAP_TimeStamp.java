package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_TimeStamp extends NGAP_OctetString {

    public NGAP_TimeStamp(OctetString value) {
        super(value);
    }

    public NGAP_TimeStamp(BitString value) {
        super(value);
    }

    public NGAP_TimeStamp(Octet[] octets) {
        super(octets);
    }

    public NGAP_TimeStamp(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_TimeStamp(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_TimeStamp(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "TimeStamp";
    }

    @Override
    public String getXmlTagName() {
        return "TimeStamp";
    }
}
