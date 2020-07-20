package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_ExpectedActivityPeriod extends NGAP_Integer {

    public NGAP_ExpectedActivityPeriod(long value) {
        super(value);
    }

    public NGAP_ExpectedActivityPeriod(Octet value) {
        super(value);
    }

    public NGAP_ExpectedActivityPeriod(Octet2 value) {
        super(value);
    }

    public NGAP_ExpectedActivityPeriod(Octet3 value) {
        super(value);
    }

    public NGAP_ExpectedActivityPeriod(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ExpectedActivityPeriod";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedActivityPeriod";
    }
}
