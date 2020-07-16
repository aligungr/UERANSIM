package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_DirectForwardingPathAvailability;

public class NGAP_HandoverRequiredTransfer extends NgapSequence {

    public NGAP_DirectForwardingPathAvailability directForwardingPathAvailability;

    @Override
    protected String getAsnName() {
        return "HandoverRequiredTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "HandoverRequiredTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"directForwardingPathAvailability"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"directForwardingPathAvailability"};
    }
}
