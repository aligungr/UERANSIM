package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_NASSecurityParametersFromNGRAN extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "NASSecurityParametersFromNGRAN";
    }

    @Override
    protected String getXmlTagName() {
        return "NASSecurityParametersFromNGRAN";
    }
}
