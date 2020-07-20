package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_QosFlowPerTNLInformation extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation uPTransportLayerInformation;
    public NGAP_AssociatedQosFlowList associatedQosFlowList;

    @Override
    public String getAsnName() {
        return "QosFlowPerTNLInformation";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowPerTNLInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uPTransportLayerInformation", "associatedQosFlowList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uPTransportLayerInformation", "associatedQosFlowList"};
    }
}
