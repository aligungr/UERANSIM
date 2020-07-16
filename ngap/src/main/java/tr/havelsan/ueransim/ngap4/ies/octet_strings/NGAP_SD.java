package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_SD extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "SD";
    }

    @Override
    protected String getXmlTagName() {
        return "SD";
    }
}
