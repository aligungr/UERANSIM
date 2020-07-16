package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AssociatedQosFlowList;

public class NGAP_QosFlowPerTNLInformation extends NgapSequence {

    public NGAP_UPTransportLayerInformation uPTransportLayerInformation;
    public NGAP_AssociatedQosFlowList associatedQosFlowList;

    @Override
    protected String getAsnName() {
        return "QosFlowPerTNLInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowPerTNLInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uPTransportLayerInformation", "associatedQosFlowList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uPTransportLayerInformation", "associatedQosFlowList"};
    }
}
