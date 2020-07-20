package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NumberOfBroadcasts extends NGAP_Integer {

    public NGAP_NumberOfBroadcasts(long value) {
        super(value);
    }

    public NGAP_NumberOfBroadcasts(Octet value) {
        super(value);
    }

    public NGAP_NumberOfBroadcasts(Octet2 value) {
        super(value);
    }

    public NGAP_NumberOfBroadcasts(Octet3 value) {
        super(value);
    }

    public NGAP_NumberOfBroadcasts(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "NumberOfBroadcasts";
    }

    @Override
    public String getXmlTagName() {
        return "NumberOfBroadcasts";
    }
}
