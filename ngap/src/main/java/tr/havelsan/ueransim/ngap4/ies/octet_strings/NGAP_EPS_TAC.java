package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_EPS_TAC extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "EPS-TAC";
    }

    @Override
    protected String getXmlTagName() {
        return "EPS-TAC";
    }
}
