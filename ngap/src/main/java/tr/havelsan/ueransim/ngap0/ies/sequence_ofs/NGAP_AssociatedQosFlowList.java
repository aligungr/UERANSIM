package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_AssociatedQosFlowList extends NGAP_SequenceOf<NGAP_AssociatedQosFlowItem> {

    public NGAP_AssociatedQosFlowList() {
        super();
    }

    public NGAP_AssociatedQosFlowList(List<NGAP_AssociatedQosFlowItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AssociatedQosFlowList";
    }

    @Override
    public String getXmlTagName() {
        return "AssociatedQosFlowList";
    }

    @Override
    public Class<NGAP_AssociatedQosFlowItem> getItemType() {
        return NGAP_AssociatedQosFlowItem.class;
    }
}
