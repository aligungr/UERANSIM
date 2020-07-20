package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_QosFlowSetupResponseItemHOReqAck extends NGAP_Sequence {

    public NGAP_QosFlowIdentifier qosFlowIdentifier;
    public NGAP_DataForwardingAccepted dataForwardingAccepted;

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseItemHOReqAck";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseItemHOReqAck";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowIdentifier", "dataForwardingAccepted"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowIdentifier", "dataForwardingAccepted"};
    }
}
