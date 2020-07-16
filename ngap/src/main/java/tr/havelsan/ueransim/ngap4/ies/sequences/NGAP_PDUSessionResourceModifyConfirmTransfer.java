package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowModifyConfirmList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_TNLMappingList;

public class NGAP_PDUSessionResourceModifyConfirmTransfer extends NgapSequence {

    public NGAP_QosFlowModifyConfirmList qosFlowModifyConfirmList;
    public NGAP_TNLMappingList tNLMappingList;
    public NGAP_QosFlowList qosFlowFailedToModifyList;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyConfirmTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyConfirmTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosFlowModifyConfirmList", "tNLMappingList", "qosFlowFailedToModifyList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosFlowModifyConfirmList", "tNLMappingList", "qosFlowFailedToModifyList"};
    }
}
