package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowInformationList extends NGAP_SequenceOf<NGAP_QosFlowInformationItem> {

    public NGAP_QosFlowInformationList() {
        super();
    }

    public NGAP_QosFlowInformationList(List<NGAP_QosFlowInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowInformationList";
    }

    @Override
    public Class<NGAP_QosFlowInformationItem> getItemType() {
        return NGAP_QosFlowInformationItem.class;
    }
}
