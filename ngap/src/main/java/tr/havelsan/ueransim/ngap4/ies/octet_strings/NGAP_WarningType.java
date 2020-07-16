package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_WarningType extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "WarningType";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningType";
    }
}
