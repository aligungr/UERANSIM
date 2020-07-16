package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowItem;

public class NGAP_QosFlowList extends NgapSequenceOf<NGAP_QosFlowItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowList";
    }

    @Override
    public Class<NGAP_QosFlowItem> getItemType() {
        return NGAP_QosFlowItem.class;
    }
}
