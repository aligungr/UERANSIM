package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowAcceptedItem;

public class NGAP_QosFlowAcceptedList extends NgapSequenceOf<NGAP_QosFlowAcceptedItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowAcceptedList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowAcceptedList";
    }

    @Override
    public Class<NGAP_QosFlowAcceptedItem> getItemType() {
        return NGAP_QosFlowAcceptedItem.class;
    }
}
