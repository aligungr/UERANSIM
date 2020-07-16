package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_NAS_PDU extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "NAS-PDU";
    }

    @Override
    protected String getXmlTagName() {
        return "NAS-PDU";
    }
}
