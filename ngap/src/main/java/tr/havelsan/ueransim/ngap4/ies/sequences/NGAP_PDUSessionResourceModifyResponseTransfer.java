package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowAddOrModifyResponseList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowList;

public class NGAP_PDUSessionResourceModifyResponseTransfer extends NgapSequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_QosFlowAddOrModifyResponseList qosFlowAddOrModifyResponseList;
    public NGAP_QosFlowPerTNLInformation additionalQosFlowPerTNLInformation;
    public NGAP_QosFlowList qosFlowFailedToAddOrModifyList;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyResponseTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyResponseTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dL-NGU-UP-TNLInformation", "uL-NGU-UP-TNLInformation", "qosFlowAddOrModifyResponseList", "additionalQosFlowPerTNLInformation", "qosFlowFailedToAddOrModifyList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dL_NGU_UP_TNLInformation", "uL_NGU_UP_TNLInformation", "qosFlowAddOrModifyResponseList", "additionalQosFlowPerTNLInformation", "qosFlowFailedToAddOrModifyList"};
    }
}
