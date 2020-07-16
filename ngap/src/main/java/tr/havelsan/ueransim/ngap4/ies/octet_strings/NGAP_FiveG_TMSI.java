package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_FiveG_TMSI extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "FiveG-TMSI";
    }

    @Override
    protected String getXmlTagName() {
        return "FiveG-TMSI";
    }
}
