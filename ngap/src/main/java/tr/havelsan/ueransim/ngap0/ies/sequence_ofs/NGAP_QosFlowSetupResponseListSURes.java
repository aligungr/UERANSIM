package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_QosFlowSetupResponseListSURes extends NGAP_SequenceOf<NGAP_QosFlowSetupResponseItemSURes> {

    public NGAP_QosFlowSetupResponseListSURes() {
        super();
    }

    public NGAP_QosFlowSetupResponseListSURes(List<NGAP_QosFlowSetupResponseItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemSURes> getItemType() {
        return NGAP_QosFlowSetupResponseItemSURes.class;
    }
}
