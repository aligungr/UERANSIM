package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AssociatedQosFlowItem;

public class NGAP_AssociatedQosFlowList extends NgapSequenceOf<NGAP_AssociatedQosFlowItem> {

    @Override
    protected String getAsnName() {
        return "AssociatedQosFlowList";
    }

    @Override
    protected String getXmlTagName() {
        return "AssociatedQosFlowList";
    }

    @Override
    public Class<NGAP_AssociatedQosFlowItem> getItemType() {
        return NGAP_AssociatedQosFlowItem.class;
    }
}
