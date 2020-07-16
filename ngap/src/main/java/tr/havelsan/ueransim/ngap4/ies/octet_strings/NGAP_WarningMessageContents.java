package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_WarningMessageContents extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "WarningMessageContents";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningMessageContents";
    }
}
