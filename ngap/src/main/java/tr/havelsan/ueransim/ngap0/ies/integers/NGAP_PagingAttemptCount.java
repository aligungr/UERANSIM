package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PagingAttemptCount extends NGAP_Integer {

    public NGAP_PagingAttemptCount(long value) {
        super(value);
    }

    public NGAP_PagingAttemptCount(Octet value) {
        super(value);
    }

    public NGAP_PagingAttemptCount(Octet2 value) {
        super(value);
    }

    public NGAP_PagingAttemptCount(Octet3 value) {
        super(value);
    }

    public NGAP_PagingAttemptCount(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PagingAttemptCount";
    }

    @Override
    public String getXmlTagName() {
        return "PagingAttemptCount";
    }
}
