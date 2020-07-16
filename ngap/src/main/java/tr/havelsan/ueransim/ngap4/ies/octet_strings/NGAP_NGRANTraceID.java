package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_NGRANTraceID extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "NGRANTraceID";
    }

    @Override
    protected String getXmlTagName() {
        return "NGRANTraceID";
    }
}
