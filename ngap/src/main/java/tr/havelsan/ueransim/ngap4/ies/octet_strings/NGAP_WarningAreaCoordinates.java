package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_WarningAreaCoordinates extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "WarningAreaCoordinates";
    }

    @Override
    protected String getXmlTagName() {
        return "WarningAreaCoordinates";
    }
}
