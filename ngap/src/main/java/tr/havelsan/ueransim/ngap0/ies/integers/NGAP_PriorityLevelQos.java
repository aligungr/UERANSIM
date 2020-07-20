package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PriorityLevelQos extends NGAP_Integer {

    public NGAP_PriorityLevelQos(long value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet2 value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet3 value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PriorityLevelQos";
    }

    @Override
    public String getXmlTagName() {
        return "PriorityLevelQos";
    }
}
