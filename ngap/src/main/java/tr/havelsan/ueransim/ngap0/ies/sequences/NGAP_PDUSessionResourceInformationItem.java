package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceInformationItem extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_QosFlowInformationList qosFlowInformationList;
    public NGAP_DRBsToQosFlowsMappingList dRBsToQosFlowsMappingList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "qosFlowInformationList", "dRBsToQosFlowsMappingList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "qosFlowInformationList", "dRBsToQosFlowsMappingList"};
    }
}
