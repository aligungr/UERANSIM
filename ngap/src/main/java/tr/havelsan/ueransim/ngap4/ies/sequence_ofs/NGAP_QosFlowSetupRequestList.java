package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowSetupRequestItem;

public class NGAP_QosFlowSetupRequestList extends NgapSequenceOf<NGAP_QosFlowSetupRequestItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    public Class<NGAP_QosFlowSetupRequestItem> getItemType() {
        return NGAP_QosFlowSetupRequestItem.class;
    }
}
