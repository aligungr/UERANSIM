package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_PacketDelayBudget extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "PacketDelayBudget";
    }

    @Override
    protected String getXmlTagName() {
        return "PacketDelayBudget";
    }
}
