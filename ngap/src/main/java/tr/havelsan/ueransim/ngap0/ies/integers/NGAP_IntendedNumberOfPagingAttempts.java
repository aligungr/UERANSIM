package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_IntendedNumberOfPagingAttempts extends NGAP_Integer {

    public NGAP_IntendedNumberOfPagingAttempts(long value) {
        super(value);
    }

    public NGAP_IntendedNumberOfPagingAttempts(Octet value) {
        super(value);
    }

    public NGAP_IntendedNumberOfPagingAttempts(Octet2 value) {
        super(value);
    }

    public NGAP_IntendedNumberOfPagingAttempts(Octet3 value) {
        super(value);
    }

    public NGAP_IntendedNumberOfPagingAttempts(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "IntendedNumberOfPagingAttempts";
    }

    @Override
    public String getXmlTagName() {
        return "IntendedNumberOfPagingAttempts";
    }
}
