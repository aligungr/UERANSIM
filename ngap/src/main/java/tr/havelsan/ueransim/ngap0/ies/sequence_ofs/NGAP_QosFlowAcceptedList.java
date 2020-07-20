package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowAcceptedList extends NGAP_SequenceOf<NGAP_QosFlowAcceptedItem> {

    public NGAP_QosFlowAcceptedList() {
        super();
    }

    public NGAP_QosFlowAcceptedList(List<NGAP_QosFlowAcceptedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowAcceptedList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowAcceptedList";
    }

    @Override
    public Class<NGAP_QosFlowAcceptedItem> getItemType() {
        return NGAP_QosFlowAcceptedItem.class;
    }
}
