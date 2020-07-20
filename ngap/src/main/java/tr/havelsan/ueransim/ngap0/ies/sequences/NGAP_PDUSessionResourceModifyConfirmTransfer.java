package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_PDUSessionResourceModifyConfirmTransfer extends NGAP_Sequence {

    public NGAP_QosFlowModifyConfirmList qosFlowModifyConfirmList;
    public NGAP_TNLMappingList tNLMappingList;
    public NGAP_QosFlowList qosFlowFailedToModifyList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyConfirmTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyConfirmTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowModifyConfirmList", "tNLMappingList", "qosFlowFailedToModifyList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowModifyConfirmList", "tNLMappingList", "qosFlowFailedToModifyList"};
    }
}
