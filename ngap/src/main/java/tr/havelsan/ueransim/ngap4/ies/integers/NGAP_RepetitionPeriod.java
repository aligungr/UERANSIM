package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_RepetitionPeriod extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "RepetitionPeriod";
    }

    @Override
    protected String getXmlTagName() {
        return "RepetitionPeriod";
    }
}
