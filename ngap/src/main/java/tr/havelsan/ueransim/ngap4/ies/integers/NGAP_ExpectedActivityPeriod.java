package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_ExpectedActivityPeriod extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "ExpectedActivityPeriod";
    }

    @Override
    protected String getXmlTagName() {
        return "ExpectedActivityPeriod";
    }
}
