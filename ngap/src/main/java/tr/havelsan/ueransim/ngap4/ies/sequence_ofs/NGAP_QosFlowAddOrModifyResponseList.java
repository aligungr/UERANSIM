package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowAddOrModifyResponseItem;

public class NGAP_QosFlowAddOrModifyResponseList extends NgapSequenceOf<NGAP_QosFlowAddOrModifyResponseItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowAddOrModifyResponseList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowAddOrModifyResponseList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyResponseItem> getItemType() {
        return NGAP_QosFlowAddOrModifyResponseItem.class;
    }
}
