package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_ExpectedIdlePeriod extends NGAP_Integer {

    public NGAP_ExpectedIdlePeriod(long value) {
        super(value);
    }

    public NGAP_ExpectedIdlePeriod(Octet value) {
        super(value);
    }

    public NGAP_ExpectedIdlePeriod(Octet2 value) {
        super(value);
    }

    public NGAP_ExpectedIdlePeriod(Octet3 value) {
        super(value);
    }

    public NGAP_ExpectedIdlePeriod(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ExpectedIdlePeriod";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedIdlePeriod";
    }
}
