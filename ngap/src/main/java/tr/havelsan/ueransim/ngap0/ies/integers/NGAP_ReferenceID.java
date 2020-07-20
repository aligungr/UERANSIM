package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_ReferenceID extends NGAP_Integer {

    public NGAP_ReferenceID(long value) {
        super(value);
    }

    public NGAP_ReferenceID(Octet value) {
        super(value);
    }

    public NGAP_ReferenceID(Octet2 value) {
        super(value);
    }

    public NGAP_ReferenceID(Octet3 value) {
        super(value);
    }

    public NGAP_ReferenceID(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ReferenceID";
    }

    @Override
    public String getXmlTagName() {
        return "ReferenceID";
    }
}
