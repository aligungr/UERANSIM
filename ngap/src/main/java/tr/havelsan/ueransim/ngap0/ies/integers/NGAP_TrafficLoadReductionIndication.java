package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_TrafficLoadReductionIndication extends NGAP_Integer {

    public NGAP_TrafficLoadReductionIndication(long value) {
        super(value);
    }

    public NGAP_TrafficLoadReductionIndication(Octet value) {
        super(value);
    }

    public NGAP_TrafficLoadReductionIndication(Octet2 value) {
        super(value);
    }

    public NGAP_TrafficLoadReductionIndication(Octet3 value) {
        super(value);
    }

    public NGAP_TrafficLoadReductionIndication(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TrafficLoadReductionIndication";
    }

    @Override
    public String getXmlTagName() {
        return "TrafficLoadReductionIndication";
    }
}
