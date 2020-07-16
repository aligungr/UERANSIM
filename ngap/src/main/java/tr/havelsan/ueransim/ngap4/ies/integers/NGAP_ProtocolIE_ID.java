package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_ProtocolIE_ID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "ProtocolIE-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "ProtocolIE-ID";
    }
}
