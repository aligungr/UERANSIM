package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_DRB_ID extends NGAP_Integer {

    public NGAP_DRB_ID(long value) {
        super(value);
    }

    public NGAP_DRB_ID(Octet value) {
        super(value);
    }

    public NGAP_DRB_ID(Octet2 value) {
        super(value);
    }

    public NGAP_DRB_ID(Octet3 value) {
        super(value);
    }

    public NGAP_DRB_ID(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "DRB-ID";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-ID";
    }
}
