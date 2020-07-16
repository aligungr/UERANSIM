package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_DRB_ID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "DRB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "DRB-ID";
    }
}
