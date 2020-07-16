package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_WarningSecurityInfo extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "WarningSecurityInfo";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningSecurityInfo";
    }
}
