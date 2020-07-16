package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_NetworkInstance extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "NetworkInstance";
    }

    @Override
    protected String getXmlTagName() {
        return "NetworkInstance";
    }
}
