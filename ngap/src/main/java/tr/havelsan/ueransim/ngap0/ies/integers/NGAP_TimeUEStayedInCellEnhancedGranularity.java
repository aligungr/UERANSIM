package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_TimeUEStayedInCellEnhancedGranularity extends NGAP_Integer {

    public NGAP_TimeUEStayedInCellEnhancedGranularity(long value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCellEnhancedGranularity(Octet value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCellEnhancedGranularity(Octet2 value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCellEnhancedGranularity(Octet3 value) {
        super(value);
    }

    public NGAP_TimeUEStayedInCellEnhancedGranularity(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TimeUEStayedInCellEnhancedGranularity";
    }

    @Override
    public String getXmlTagName() {
        return "TimeUEStayedInCellEnhancedGranularity";
    }
}
