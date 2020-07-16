package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_DLForwarding;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_QosFlowIdentifier;

public class NGAP_QosFlowInformationItem extends NgapSequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_DLForwarding dLForwarding;

    @Override
    protected String getAsnName() {
        return "QosFlowInformationItem";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowInformationItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "dLForwarding"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "dLForwarding"};
    }
}
