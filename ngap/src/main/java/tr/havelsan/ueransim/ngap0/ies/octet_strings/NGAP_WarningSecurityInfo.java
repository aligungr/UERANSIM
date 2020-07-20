package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_WarningSecurityInfo extends NGAP_OctetString {

    public NGAP_WarningSecurityInfo(OctetString value) {
        super(value);
    }

    public NGAP_WarningSecurityInfo(BitString value) {
        super(value);
    }

    public NGAP_WarningSecurityInfo(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningSecurityInfo(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningSecurityInfo(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningSecurityInfo(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "WarningSecurityInfo";
    }

    @Override
    public String getXmlTagName() {
        return "WarningSecurityInfo";
    }
}
