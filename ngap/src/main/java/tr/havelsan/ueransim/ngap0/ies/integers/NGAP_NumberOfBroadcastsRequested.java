package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_NumberOfBroadcastsRequested extends NGAP_Integer {

    public NGAP_NumberOfBroadcastsRequested(long value) {
        super(value);
    }

    public NGAP_NumberOfBroadcastsRequested(Octet value) {
        super(value);
    }

    public NGAP_NumberOfBroadcastsRequested(Octet2 value) {
        super(value);
    }

    public NGAP_NumberOfBroadcastsRequested(Octet3 value) {
        super(value);
    }

    public NGAP_NumberOfBroadcastsRequested(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "NumberOfBroadcastsRequested";
    }

    @Override
    public String getXmlTagName() {
        return "NumberOfBroadcastsRequested";
    }
}
