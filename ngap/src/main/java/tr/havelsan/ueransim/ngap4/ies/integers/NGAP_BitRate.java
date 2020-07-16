package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_BitRate extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "BitRate";
    }

    @Override
    protected String getXmlTagName() {
        return "BitRate";
    }
}
