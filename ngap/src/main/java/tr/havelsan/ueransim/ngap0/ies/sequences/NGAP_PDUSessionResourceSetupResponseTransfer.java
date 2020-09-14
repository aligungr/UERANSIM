package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_PDUSessionResourceSetupResponseTransfer extends NGAP_Sequence {

    public NGAP_QosFlowPerTNLInformation dLQosFlowPerTNLInformation;
    public NGAP_QosFlowPerTNLInformationList additionalDLQosFlowPerTNLInformation;
    public NGAP_SecurityResult securityResult;
    public NGAP_QosFlowListWithCause qosFlowFailedToSetupList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupResponseTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupResponseTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dLQosFlowPerTNLInformation", "additionalDLQosFlowPerTNLInformation", "securityResult", "qosFlowFailedToSetupList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dLQosFlowPerTNLInformation", "additionalDLQosFlowPerTNLInformation", "securityResult", "qosFlowFailedToSetupList"};
    }
}
