package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_RAN_UE_NGAP_ID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "RAN-UE-NGAP-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "RAN-UE-NGAP-ID";
    }
}
