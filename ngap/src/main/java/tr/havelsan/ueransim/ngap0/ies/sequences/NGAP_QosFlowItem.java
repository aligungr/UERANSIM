package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_QosFlowItem extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_Cause cause;

    @Override
    public String getAsnName() {
        return "QosFlowItem";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "cause"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "cause"};
    }
}
