package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PacketLossRate extends NGAP_Integer {

    public NGAP_PacketLossRate(long value) {
        super(value);
    }

    public NGAP_PacketLossRate(Octet value) {
        super(value);
    }

    public NGAP_PacketLossRate(Octet2 value) {
        super(value);
    }

    public NGAP_PacketLossRate(Octet3 value) {
        super(value);
    }

    public NGAP_PacketLossRate(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PacketLossRate";
    }

    @Override
    public String getXmlTagName() {
        return "PacketLossRate";
    }
}
