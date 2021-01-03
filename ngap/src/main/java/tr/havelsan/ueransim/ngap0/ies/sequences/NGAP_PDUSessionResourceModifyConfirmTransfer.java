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
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowListWithCause;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowModifyConfirmList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_UPTransportLayerInformationPairList;

public class NGAP_PDUSessionResourceModifyConfirmTransfer extends NGAP_Sequence {

    public NGAP_QosFlowModifyConfirmList qosFlowModifyConfirmList;
    public NGAP_UPTransportLayerInformation uLNGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformationPairList additionalNG_UUPTNLInformation;
    public NGAP_QosFlowListWithCause qosFlowFailedToModifyList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyConfirmTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyConfirmTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowModifyConfirmList", "uLNGU-UP-TNLInformation", "additionalNG-UUPTNLInformation", "qosFlowFailedToModifyList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowModifyConfirmList", "uLNGU_UP_TNLInformation", "additionalNG_UUPTNLInformation", "qosFlowFailedToModifyList"};
    }
}
