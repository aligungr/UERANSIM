package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_HandoverRequiredTransfer extends NGAP_Sequence {

    public NGAP_DirectForwardingPathAvailability directForwardingPathAvailability;

    @Override
    public String getAsnName() {
        return "HandoverRequiredTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverRequiredTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"directForwardingPathAvailability"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"directForwardingPathAvailability"};
    }
}
