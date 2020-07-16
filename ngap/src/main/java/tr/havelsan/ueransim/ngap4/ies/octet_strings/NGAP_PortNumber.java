package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_PortNumber extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "PortNumber";
    }

    @Override
    protected String getXmlTagName() {
        return "PortNumber";
    }
}
