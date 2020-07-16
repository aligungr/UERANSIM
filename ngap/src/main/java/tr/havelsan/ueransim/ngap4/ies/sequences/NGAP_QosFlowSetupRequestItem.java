package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_E_RAB_ID;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_QosFlowIdentifier;

public class NGAP_QosFlowSetupRequestItem extends NgapSequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_QosFlowLevelQosParameters qosFlowLevelQosParameters;
    public NGAP_E_RAB_ID e_RAB_ID;

    @Override
    protected String getAsnName() {
        return "QosFlowSetupRequestItem";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowSetupRequestItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "qosFlowLevelQosParameters", "e-RAB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "qosFlowLevelQosParameters", "e_RAB_ID"};
    }
}
