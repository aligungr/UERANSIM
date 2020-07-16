package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_ReferenceID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "ReferenceID";
    }

    @Override
    protected String getXmlTagName() {
        return "ReferenceID";
    }
}
