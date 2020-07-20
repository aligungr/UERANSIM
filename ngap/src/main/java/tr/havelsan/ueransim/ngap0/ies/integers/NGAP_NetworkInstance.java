package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NetworkInstance extends NGAP_Integer {

    public NGAP_NetworkInstance(long value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet2 value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet3 value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "NetworkInstance";
    }

    @Override
    public String getXmlTagName() {
        return "NetworkInstance";
    }
}
