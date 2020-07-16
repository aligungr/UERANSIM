package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowToBeForwardedItem;

public class NGAP_QosFlowToBeForwardedList extends NgapSequenceOf<NGAP_QosFlowToBeForwardedItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowToBeForwardedList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowToBeForwardedList";
    }

    @Override
    public Class<NGAP_QosFlowToBeForwardedItem> getItemType() {
        return NGAP_QosFlowToBeForwardedItem.class;
    }
}
