package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_TimeStamp extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "TimeStamp";
    }

    @Override
    protected String getXmlTagName() {
        return "TimeStamp";
    }
}
