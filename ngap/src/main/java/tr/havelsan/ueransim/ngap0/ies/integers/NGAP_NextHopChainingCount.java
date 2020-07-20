package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NextHopChainingCount extends NGAP_Integer {

    public NGAP_NextHopChainingCount(long value) {
        super(value);
    }

    public NGAP_NextHopChainingCount(Octet value) {
        super(value);
    }

    public NGAP_NextHopChainingCount(Octet2 value) {
        super(value);
    }

    public NGAP_NextHopChainingCount(Octet3 value) {
        super(value);
    }

    public NGAP_NextHopChainingCount(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "NextHopChainingCount";
    }

    @Override
    public String getXmlTagName() {
        return "NextHopChainingCount";
    }
}
