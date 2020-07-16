package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_SST extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "SST";
    }

    @Override
    protected String getXmlTagName() {
        return "SST";
    }
}
