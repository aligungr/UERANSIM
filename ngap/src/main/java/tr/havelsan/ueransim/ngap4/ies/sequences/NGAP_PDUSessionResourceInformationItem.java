package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_DRBsToQosFlowsMappingList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowInformationList;

public class NGAP_PDUSessionResourceInformationItem extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_QosFlowInformationList qosFlowInformationList;
    public NGAP_DRBsToQosFlowsMappingList dRBsToQosFlowsMappingList;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceInformationItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceInformationItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "qosFlowInformationList", "dRBsToQosFlowsMappingList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "qosFlowInformationList", "dRBsToQosFlowsMappingList"};
    }
}
