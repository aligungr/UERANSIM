package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowInformationItem;

public class NGAP_QosFlowInformationList extends NgapSequenceOf<NGAP_QosFlowInformationItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowInformationList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowInformationList";
    }

    @Override
    public Class<NGAP_QosFlowInformationItem> getItemType() {
        return NGAP_QosFlowInformationItem.class;
    }
}
