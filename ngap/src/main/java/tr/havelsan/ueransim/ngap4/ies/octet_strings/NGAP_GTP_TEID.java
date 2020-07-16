package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_GTP_TEID extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "GTP-TEID";
    }

    @Override
    protected String getXmlTagName() {
        return "GTP-TEID";
    }
}
