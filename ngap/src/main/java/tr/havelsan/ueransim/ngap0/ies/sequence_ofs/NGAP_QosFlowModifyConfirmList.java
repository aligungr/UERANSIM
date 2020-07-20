package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowModifyConfirmList extends NGAP_SequenceOf<NGAP_QosFlowModifyConfirmItem> {

    public NGAP_QosFlowModifyConfirmList() {
        super();
    }

    public NGAP_QosFlowModifyConfirmList(List<NGAP_QosFlowModifyConfirmItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public Class<NGAP_QosFlowModifyConfirmItem> getItemType() {
        return NGAP_QosFlowModifyConfirmItem.class;
    }
}
