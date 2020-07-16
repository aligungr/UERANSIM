package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_QosFlowIdentifier extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "QosFlowIdentifier";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowIdentifier";
    }
}
