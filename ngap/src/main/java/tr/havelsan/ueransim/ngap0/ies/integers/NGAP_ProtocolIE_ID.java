package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_ProtocolIE_ID extends NGAP_Integer {

    public NGAP_ProtocolIE_ID(long value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet2 value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet3 value) {
        super(value);
    }

    public NGAP_ProtocolIE_ID(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ProtocolIE-ID";
    }

    @Override
    public String getXmlTagName() {
        return "ProtocolIE-ID";
    }
}
