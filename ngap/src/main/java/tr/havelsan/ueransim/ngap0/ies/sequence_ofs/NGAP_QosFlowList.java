package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowList extends NGAP_SequenceOf<NGAP_QosFlowItem> {

    public NGAP_QosFlowList() {
        super();
    }

    public NGAP_QosFlowList(List<NGAP_QosFlowItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowList";
    }

    @Override
    public Class<NGAP_QosFlowItem> getItemType() {
        return NGAP_QosFlowItem.class;
    }
}
