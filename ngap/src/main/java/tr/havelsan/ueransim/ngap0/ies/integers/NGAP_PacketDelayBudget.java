package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PacketDelayBudget extends NGAP_Integer {

    public NGAP_PacketDelayBudget(long value) {
        super(value);
    }

    public NGAP_PacketDelayBudget(Octet value) {
        super(value);
    }

    public NGAP_PacketDelayBudget(Octet2 value) {
        super(value);
    }

    public NGAP_PacketDelayBudget(Octet3 value) {
        super(value);
    }

    public NGAP_PacketDelayBudget(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PacketDelayBudget";
    }

    @Override
    public String getXmlTagName() {
        return "PacketDelayBudget";
    }
}
