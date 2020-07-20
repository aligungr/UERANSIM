package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_RelativeAMFCapacity extends NGAP_Integer {

    public NGAP_RelativeAMFCapacity(long value) {
        super(value);
    }

    public NGAP_RelativeAMFCapacity(Octet value) {
        super(value);
    }

    public NGAP_RelativeAMFCapacity(Octet2 value) {
        super(value);
    }

    public NGAP_RelativeAMFCapacity(Octet3 value) {
        super(value);
    }

    public NGAP_RelativeAMFCapacity(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RelativeAMFCapacity";
    }

    @Override
    public String getXmlTagName() {
        return "RelativeAMFCapacity";
    }
}
