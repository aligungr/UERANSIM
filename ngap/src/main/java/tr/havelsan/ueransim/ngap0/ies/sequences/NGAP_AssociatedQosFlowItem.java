package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_AssociatedQosFlowItem extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_Integer qosFlowMappingIndication;

    @Override
    public String getAsnName() {
        return "AssociatedQosFlowItem";
    }

    @Override
    public String getXmlTagName() {
        return "AssociatedQosFlowItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "qosFlowMappingIndication"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "qosFlowMappingIndication"};
    }
}
