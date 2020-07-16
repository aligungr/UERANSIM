package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_NextHopChainingCount extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "NextHopChainingCount";
    }

    @Override
    protected String getXmlTagName() {
        return "NextHopChainingCount";
    }
}
