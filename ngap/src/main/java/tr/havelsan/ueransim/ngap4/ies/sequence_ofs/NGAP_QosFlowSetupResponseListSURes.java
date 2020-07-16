package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_QosFlowSetupResponseItemSURes;

public class NGAP_QosFlowSetupResponseListSURes extends NgapSequenceOf<NGAP_QosFlowSetupResponseItemSURes> {

    @Override
    protected String getAsnName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemSURes> getItemType() {
        return NGAP_QosFlowSetupResponseItemSURes.class;
    }
}
