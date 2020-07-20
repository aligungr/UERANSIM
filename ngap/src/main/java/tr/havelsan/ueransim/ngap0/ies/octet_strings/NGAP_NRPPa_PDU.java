package tr.havelsan.ueransim.ngap0.ies.octet_strings;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NRPPa_PDU extends NGAP_OctetString {

    public NGAP_NRPPa_PDU(OctetString value) {
        super(value);
    }

    public NGAP_NRPPa_PDU(BitString value) {
        super(value);
    }

    public NGAP_NRPPa_PDU(Octet[] octets) {
        super(octets);
    }

    public NGAP_NRPPa_PDU(int[] octetInts) {
        super(octetInts);
    }

    public NGAP_NRPPa_PDU(byte[] octetBytes) {
        super(octetBytes);
    }

    public NGAP_NRPPa_PDU(String hex) {
        super(hex);
    }

    @Override
    public String getAsnName() {
        return "NRPPa-PDU";
    }

    @Override
    public String getXmlTagName() {
        return "NRPPa-PDU";
    }
}
