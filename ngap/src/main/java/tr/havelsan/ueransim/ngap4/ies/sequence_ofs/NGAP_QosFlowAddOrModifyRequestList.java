package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowAddOrModifyRequestItem;

public class NGAP_QosFlowAddOrModifyRequestList extends NgapSequenceOf<NGAP_QosFlowAddOrModifyRequestItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowAddOrModifyRequestList";
    }

    @Override
    public Class<NGAP_QosFlowAddOrModifyRequestItem> getItemType() {
        return NGAP_QosFlowAddOrModifyRequestItem.class;
    }
}
