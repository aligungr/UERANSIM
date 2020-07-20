package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowSetupRequestList extends NGAP_SequenceOf<NGAP_QosFlowSetupRequestItem> {

    public NGAP_QosFlowSetupRequestList() {
        super();
    }

    public NGAP_QosFlowSetupRequestList(List<NGAP_QosFlowSetupRequestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    public Class<NGAP_QosFlowSetupRequestItem> getItemType() {
        return NGAP_QosFlowSetupRequestItem.class;
    }
}
