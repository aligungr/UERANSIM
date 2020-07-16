package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_TrafficLoadReductionIndication extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "TrafficLoadReductionIndication";
    }

    @Override
    protected String getXmlTagName() {
        return "TrafficLoadReductionIndication";
    }
}
