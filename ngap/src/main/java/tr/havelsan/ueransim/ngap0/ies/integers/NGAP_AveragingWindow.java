package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.utils.octets.*;

public class NGAP_AveragingWindow extends NGAP_Integer {

    public NGAP_AveragingWindow(long value) {
        super(value);
    }

    public NGAP_AveragingWindow(Octet value) {
        super(value);
    }

    public NGAP_AveragingWindow(Octet2 value) {
        super(value);
    }

    public NGAP_AveragingWindow(Octet3 value) {
        super(value);
    }

    public NGAP_AveragingWindow(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AveragingWindow";
    }

    @Override
    public String getXmlTagName() {
        return "AveragingWindow";
    }
}
