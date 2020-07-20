package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_QosFlowInformationItem extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_DLForwarding dLForwarding;

    @Override
    public String getAsnName() {
        return "QosFlowInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "dLForwarding"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "dLForwarding"};
    }
}
