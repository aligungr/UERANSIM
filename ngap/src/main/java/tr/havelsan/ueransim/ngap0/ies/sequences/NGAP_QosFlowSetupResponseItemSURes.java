package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_QosFlowSetupResponseItemSURes extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseItemSURes";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseItemSURes";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier"};
    }
}
