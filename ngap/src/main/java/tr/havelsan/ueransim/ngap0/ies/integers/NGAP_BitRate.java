package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_BitRate extends NGAP_Integer {

    public NGAP_BitRate(long value) {
        super(value);
    }

    public NGAP_BitRate(Octet value) {
        super(value);
    }

    public NGAP_BitRate(Octet2 value) {
        super(value);
    }

    public NGAP_BitRate(Octet3 value) {
        super(value);
    }

    public NGAP_BitRate(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "BitRate";
    }

    @Override
    public String getXmlTagName() {
        return "BitRate";
    }
}
