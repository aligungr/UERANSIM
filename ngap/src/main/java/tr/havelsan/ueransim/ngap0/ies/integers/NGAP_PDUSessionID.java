package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_PDUSessionID extends NGAP_Integer {

    public NGAP_PDUSessionID(long value) {
        super(value);
    }

    public NGAP_PDUSessionID(Octet value) {
        super(value);
    }

    public NGAP_PDUSessionID(Octet2 value) {
        super(value);
    }

    public NGAP_PDUSessionID(Octet3 value) {
        super(value);
    }

    public NGAP_PDUSessionID(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionID";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionID";
    }
}
