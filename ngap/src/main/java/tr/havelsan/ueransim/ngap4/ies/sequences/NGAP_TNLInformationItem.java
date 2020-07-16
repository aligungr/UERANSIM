package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_TNLInformationItem extends NgapSequence {

    public NGAP_QosFlowPerTNLInformation qosFlowPerTNLInformation;

    @Override
    protected String getAsnName() {
        return "TNLInformationItem";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLInformationItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"qosFlowPerTNLInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"qosFlowPerTNLInformation"};
    }
}
