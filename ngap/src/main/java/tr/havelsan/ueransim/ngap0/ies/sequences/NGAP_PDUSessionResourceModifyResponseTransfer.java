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
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowAddOrModifyResponseList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowListWithCause;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowPerTNLInformationList;

public class NGAP_PDUSessionResourceModifyResponseTransfer extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_QosFlowAddOrModifyResponseList qosFlowAddOrModifyResponseList;
    public NGAP_QosFlowPerTNLInformationList additionalDLQosFlowPerTNLInformation;
    public NGAP_QosFlowListWithCause qosFlowFailedToAddOrModifyList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyResponseTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyResponseTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dL-NGU-UP-TNLInformation", "uL-NGU-UP-TNLInformation", "qosFlowAddOrModifyResponseList", "additionalDLQosFlowPerTNLInformation", "qosFlowFailedToAddOrModifyList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dL_NGU_UP_TNLInformation", "uL_NGU_UP_TNLInformation", "qosFlowAddOrModifyResponseList", "additionalDLQosFlowPerTNLInformation", "qosFlowFailedToAddOrModifyList"};
    }
}
