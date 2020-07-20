package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_QosFlowSetupRequestItem extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_QosFlowLevelQosParameters qosFlowLevelQosParameters;
    public NGAP_E_RAB_ID e_RAB_ID;

    @Override
    public String getAsnName() {
        return "QosFlowSetupRequestItem";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupRequestItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "qosFlowLevelQosParameters", "e-RAB-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "qosFlowLevelQosParameters", "e_RAB_ID"};
    }
}
