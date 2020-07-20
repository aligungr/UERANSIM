package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_TimeUEStayedInCell extends NGAP_Integer {

    public NGAP_TimeUEStayedInCell(long value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet2 value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet3 value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCell(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TimeUEStayedInCell";
    }

    @Override
    public String getXmlTagName() {
        return "TimeUEStayedInCell";
    }
}
