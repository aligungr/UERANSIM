package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_QosFlowIdentifier;

public class NGAP_AssociatedQosFlowItem extends NgapSequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public long qosFlowMappingIndication;

    @Override
    protected String getAsnName() {
        return "AssociatedQosFlowItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AssociatedQosFlowItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "qosFlowMappingIndication"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "qosFlowMappingIndication"};
    }
}
