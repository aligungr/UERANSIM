package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_AMF_UE_NGAP_ID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "AMF-UE-NGAP-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-UE-NGAP-ID";
    }
}
