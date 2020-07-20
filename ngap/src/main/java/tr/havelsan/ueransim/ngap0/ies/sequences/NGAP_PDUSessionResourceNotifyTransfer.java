package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_PDUSessionResourceNotifyTransfer extends NGAP_Sequence {

    public NGAP_QosFlowNotifyList qosFlowNotifyList;
    public NGAP_QosFlowList qosFlowReleasedList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceNotifyTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceNotifyTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowNotifyList", "qosFlowReleasedList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowNotifyList", "qosFlowReleasedList"};
    }
}
