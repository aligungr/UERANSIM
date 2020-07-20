package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowAddOrModifyResponseList extends NGAP_SequenceOf<NGAP_QosFlowAddOrModifyResponseItem> {

    public NGAP_QosFlowAddOrModifyResponseList() {
        super();
    }

    public NGAP_QosFlowAddOrModifyResponseList(List<NGAP_QosFlowAddOrModifyResponseItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowAddOrModifyResponseList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowAddOrModifyResponseList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyResponseItem> getItemType() {
        return NGAP_QosFlowAddOrModifyResponseItem.class;
    }
}
