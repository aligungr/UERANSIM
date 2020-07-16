package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_QosFlowIdentifier;

public class NGAP_QosFlowAddOrModifyResponseItem extends NgapSequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;

    @Override
    protected String getAsnName() {
        return "QosFlowAddOrModifyResponseItem";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowAddOrModifyResponseItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier"};
    }
}
