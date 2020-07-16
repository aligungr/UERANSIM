package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_ProtocolExtensionID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "ProtocolExtensionID";
    }

    @Override
    protected String getXmlTagName() {
        return "ProtocolExtensionID";
    }
}
