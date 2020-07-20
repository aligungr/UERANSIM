package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_PDUSessionResourceSetupResponseTransfer extends NGAP_Sequence {

    public NGAP_QosFlowPerTNLInformation qosFlowPerTNLInformation;
    public NGAP_QosFlowPerTNLInformation additionalQosFlowPerTNLInformation;
    public NGAP_SecurityResult securityResult;
    public NGAP_QosFlowList qosFlowFailedToSetupList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupResponseTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupResponseTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowPerTNLInformation", "additionalQosFlowPerTNLInformation", "securityResult", "qosFlowFailedToSetupList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowPerTNLInformation", "additionalQosFlowPerTNLInformation", "securityResult", "qosFlowFailedToSetupList"};
    }
}
