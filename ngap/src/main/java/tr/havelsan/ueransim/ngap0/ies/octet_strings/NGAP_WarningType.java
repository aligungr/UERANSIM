package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_WarningType extends NGAP_OctetString {

    public NGAP_WarningType(OctetString value) {
        super(value);
    }

    public NGAP_WarningType(BitString value) {
        super(value);
    }

    public NGAP_WarningType(Octet[] octets) {
        super(octets);
    }

    public NGAP_WarningType(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_WarningType(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_WarningType(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "WarningType";
    }

    @Override
    public String getXmlTagName() {
        return "WarningType";
    }
}
