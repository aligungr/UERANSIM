/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_DataForwardingResponseDRBList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowListWithCause;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowListWithDataForwarding;

public class NGAP_HandoverRequestAcknowledgeTransfer extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_SecurityResult securityResult;
    public NGAP_QosFlowListWithDataForwarding qosFlowSetupResponseList;
    public NGAP_QosFlowListWithCause qosFlowFailedToSetupList;
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
