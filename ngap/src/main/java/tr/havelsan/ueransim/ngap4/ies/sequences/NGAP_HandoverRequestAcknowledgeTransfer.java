package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_HandoverRequestAcknowledgeTransfer extends NgapSequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_SecurityResult securityResult;
    public NGAP_QosFlowSetupResponseListHOReqAck qosFlowSetupResponseList;
    public NGAP_QosFlowList qosFlowFailedToSetupList;
    public NGAP_DataForwardingResponseDRBList dataForwardingResponseDRBList;

    @Override
    public String getAsnName() {
        return "HandoverRequestAcknowledgeTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverRequestAcknowledgeTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dL-NGU-UP-TNLInformation", "dLForwardingUP-TNLInformation", "securityResult", "qosFlowSetupResponseList", "qosFlowFailedToSetupList", "dataForwardingResponseDRBList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dL_NGU_UP_TNLInformation", "dLForwardingUP_TNLInformation", "securityResult", "qosFlowSetupResponseList", "qosFlowFailedToSetupList", "dataForwardingResponseDRBList"};
    }
}
