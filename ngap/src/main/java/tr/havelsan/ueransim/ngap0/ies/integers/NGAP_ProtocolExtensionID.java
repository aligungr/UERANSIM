package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_ProtocolExtensionID extends NGAP_Integer {

    public NGAP_ProtocolExtensionID(long value) {
        super(value);
    }

    public NGAP_ProtocolExtensionID(Octet value) {
        super(value);
    }

    public NGAP_ProtocolExtensionID(Octet2 value) {
        super(value);
    }

    public NGAP_ProtocolExtensionID(Octet3 value) {
        super(value);
    }

    public NGAP_ProtocolExtensionID(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ProtocolExtensionID";
    }

    @Override
    public String getXmlTagName() {
        return "ProtocolExtensionID";
    }
}
