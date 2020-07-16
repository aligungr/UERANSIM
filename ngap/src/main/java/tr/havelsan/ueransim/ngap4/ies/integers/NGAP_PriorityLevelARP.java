package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_PriorityLevelARP extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "PriorityLevelARP";
    }

    @Override
    protected String getXmlTagName() {
        return "PriorityLevelARP";
    }
}
