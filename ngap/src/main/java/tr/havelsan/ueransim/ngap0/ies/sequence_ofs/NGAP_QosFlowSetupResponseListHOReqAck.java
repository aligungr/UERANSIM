package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowSetupResponseListHOReqAck extends NGAP_SequenceOf<NGAP_QosFlowSetupResponseItemHOReqAck> {

    public NGAP_QosFlowSetupResponseListHOReqAck() {
        super();
    }

    public NGAP_QosFlowSetupResponseListHOReqAck(List<NGAP_QosFlowSetupResponseItemHOReqAck> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseListHOReqAck";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseListHOReqAck";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemHOReqAck> getItemType() {
        return NGAP_QosFlowSetupResponseItemHOReqAck.class;
    }
}
