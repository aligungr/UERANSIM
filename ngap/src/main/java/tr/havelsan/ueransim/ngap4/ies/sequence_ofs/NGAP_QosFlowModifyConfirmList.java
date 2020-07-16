package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowModifyConfirmItem;

public class NGAP_QosFlowModifyConfirmList extends NgapSequenceOf<NGAP_QosFlowModifyConfirmItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowModifyConfirmList";
    }

    @Override
    public Class<NGAP_QosFlowModifyConfirmItem> getItemType() {
        return NGAP_QosFlowModifyConfirmItem.class;
    }
}
