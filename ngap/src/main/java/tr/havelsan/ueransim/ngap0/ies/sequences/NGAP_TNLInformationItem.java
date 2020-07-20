package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_TNLInformationItem extends NGAP_Sequence {

    public NGAP_QosFlowPerTNLInformation qosFlowPerTNLInformation;

    @Override
    public String getAsnName() {
        return "TNLInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "TNLInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowPerTNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowPerTNLInformation"};
    }
}
