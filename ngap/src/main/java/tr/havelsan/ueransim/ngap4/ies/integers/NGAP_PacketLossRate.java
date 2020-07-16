package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_PacketLossRate extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "PacketLossRate";
    }

    @Override
    protected String getXmlTagName() {
        return "PacketLossRate";
    }
}
