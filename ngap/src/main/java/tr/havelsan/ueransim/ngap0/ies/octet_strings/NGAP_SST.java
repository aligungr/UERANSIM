package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_SST extends NGAP_OctetString {

    public NGAP_SST(OctetString value) {
        super(value);
    }

    public NGAP_SST(BitString value) {
        super(value);
    }

    public NGAP_SST(Octet[] octets) {
        super(octets);
    }

    public NGAP_SST(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_SST(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_SST(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "SST";
    }

    @Override
    public String getXmlTagName() {
        return "SST";
    }
}
