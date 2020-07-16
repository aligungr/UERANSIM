package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowSetupResponseItemHOReqAck;

public class NGAP_QosFlowSetupResponseListHOReqAck extends NgapSequenceOf<NGAP_QosFlowSetupResponseItemHOReqAck> {

    @Override
    protected String getAsnName() {
        return "QosFlowSetupResponseListHOReqAck";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowSetupResponseListHOReqAck";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemHOReqAck> getItemType() {
        return NGAP_QosFlowSetupResponseItemHOReqAck.class;
    }
}
