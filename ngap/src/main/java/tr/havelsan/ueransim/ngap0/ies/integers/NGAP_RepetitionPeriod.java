package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_RepetitionPeriod extends NGAP_Integer {

    public NGAP_RepetitionPeriod(long value) {
        super(value);
    }

    public NGAP_RepetitionPeriod(Octet value) {
        super(value);
    }

    public NGAP_RepetitionPeriod(Octet2 value) {
        super(value);
    }

    public NGAP_RepetitionPeriod(Octet3 value) {
        super(value);
    }

    public NGAP_RepetitionPeriod(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RepetitionPeriod";
    }

    @Override
    public String getXmlTagName() {
        return "RepetitionPeriod";
    }
}
