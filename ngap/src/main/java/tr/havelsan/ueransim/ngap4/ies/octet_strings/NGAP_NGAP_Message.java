package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_NGAP_Message extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "NGAP-Message";
    }

    @Override
    protected String getXmlTagName() {
        return "NGAP-Message";
    }
}
