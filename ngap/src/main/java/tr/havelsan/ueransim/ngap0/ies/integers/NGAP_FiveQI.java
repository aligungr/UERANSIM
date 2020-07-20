package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_FiveQI extends NGAP_Integer {

    public NGAP_FiveQI(long value) {
        super(value);
    }

    public NGAP_FiveQI(Octet value) {
        super(value);
    }

    public NGAP_FiveQI(Octet2 value) {
        super(value);
    }

    public NGAP_FiveQI(Octet3 value) {
        super(value);
    }

    public NGAP_FiveQI(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "FiveQI";
    }

    @Override
    public String getXmlTagName() {
        return "FiveQI";
    }
}
