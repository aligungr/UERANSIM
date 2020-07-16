package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_RelativeAMFCapacity extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "RelativeAMFCapacity";
    }

    @Override
    protected String getXmlTagName() {
        return "RelativeAMFCapacity";
    }
}
