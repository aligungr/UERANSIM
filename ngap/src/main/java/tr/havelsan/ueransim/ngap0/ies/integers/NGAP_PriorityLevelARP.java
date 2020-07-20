package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PriorityLevelARP extends NGAP_Integer {

    public NGAP_PriorityLevelARP(long value) {
        super(value);
    }

    public NGAP_PriorityLevelARP(Octet value) {
        super(value);
    }

    public NGAP_PriorityLevelARP(Octet2 value) {
        super(value);
    }

    public NGAP_PriorityLevelARP(Octet3 value) {
        super(value);
    }

    public NGAP_PriorityLevelARP(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PriorityLevelARP";
    }

    @Override
    public String getXmlTagName() {
        return "PriorityLevelARP";
    }
}
