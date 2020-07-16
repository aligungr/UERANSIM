package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowNotifyItem;

public class NGAP_QosFlowNotifyList extends NgapSequenceOf<NGAP_QosFlowNotifyItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowNotifyList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowNotifyList";
    }

    @Override
    public Class<NGAP_QosFlowNotifyItem> getItemType() {
        return NGAP_QosFlowNotifyItem.class;
    }
}
