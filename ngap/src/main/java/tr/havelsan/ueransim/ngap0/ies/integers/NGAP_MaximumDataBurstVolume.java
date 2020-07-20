package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_MaximumDataBurstVolume extends NGAP_Integer {

    public NGAP_MaximumDataBurstVolume(long value) {
        super(value);
    }

    public NGAP_MaximumDataBurstVolume(Octet value) {
        super(value);
    }

    public NGAP_MaximumDataBurstVolume(Octet2 value) {
        super(value);
    }

    public NGAP_MaximumDataBurstVolume(Octet3 value) {
        super(value);
    }

    public NGAP_MaximumDataBurstVolume(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "MaximumDataBurstVolume";
    }

    @Override
    public String getXmlTagName() {
        return "MaximumDataBurstVolume";
    }
}
