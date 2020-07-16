package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_FiveQI extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "FiveQI";
    }

    @Override
    protected String getXmlTagName() {
        return "FiveQI";
    }
}
