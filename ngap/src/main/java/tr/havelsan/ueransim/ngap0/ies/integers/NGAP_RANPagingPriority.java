package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_RANPagingPriority extends NGAP_Integer {

    public NGAP_RANPagingPriority(long value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet2 value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet3 value) {
        super(value);
    }

    public NGAP_RANPagingPriority(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RANPagingPriority";
    }

    @Override
    public String getXmlTagName() {
        return "RANPagingPriority";
    }
}
