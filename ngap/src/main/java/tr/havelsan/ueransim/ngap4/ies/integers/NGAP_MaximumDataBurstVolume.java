package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_MaximumDataBurstVolume extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "MaximumDataBurstVolume";
    }

    @Override
    protected String getXmlTagName() {
        return "MaximumDataBurstVolume";
    }
}
