package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_DataForwardingResponseDRBList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowSetupResponseListHOReqAck;

public class NGAP_HandoverRequestAcknowledgeTransfer extends NgapSequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_SecurityResult securityResult;
    public NGAP_QosFlowSetupResponseListHOReqAck qosFlowSetupResponseList;
    public NGAP_QosFlowList qosFlowFailedToSetupList;
    public NGAP_DataForwardingResponseDRBList dataForwardingResponseDRBList;

    @Override
    protected String getAsnName() {
        return "HandoverRequestAcknowledgeTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "HandoverRequestAcknowledgeTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dL-NGU-UP-TNLInformation", "dLForwardingUP-TNLInformation", "securityResult", "qosFlowSetupResponseList", "qosFlowFailedToSetupList", "dataForwardingResponseDRBList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dL_NGU_UP_TNLInformation", "dLForwardingUP_TNLInformation", "securityResult", "qosFlowSetupResponseList", "qosFlowFailedToSetupList", "dataForwardingResponseDRBList"};
    }
}
