package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_AveragingWindow extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "AveragingWindow";
    }

    @Override
    protected String getXmlTagName() {
        return "AveragingWindow";
    }
}
