package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowAddOrModifyRequestList extends NGAP_SequenceOf<NGAP_QosFlowAddOrModifyRequestItem> {

    public NGAP_QosFlowAddOrModifyRequestList() {
        super();
    }

    public NGAP_QosFlowAddOrModifyRequestList(List<NGAP_QosFlowAddOrModifyRequestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyRequestItem> getItemType() {
        return NGAP_QosFlowAddOrModifyRequestItem.class;
    }
}
