package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NASSecurityParametersFromNGRAN extends NGAP_OctetString {

    public NGAP_NASSecurityParametersFromNGRAN(OctetString value) {
        super(value);
    }

    public NGAP_NASSecurityParametersFromNGRAN(BitString value) {
        super(value);
    }

    public NGAP_NASSecurityParametersFromNGRAN(Octet[] octets) {
        super(octets);
    }

    public NGAP_NASSecurityParametersFromNGRAN(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_NASSecurityParametersFromNGRAN(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_NASSecurityParametersFromNGRAN(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "NASSecurityParametersFromNGRAN";
    }

    @Override
    public String getXmlTagName() {
        return "NASSecurityParametersFromNGRAN";
    }
}
