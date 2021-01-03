/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowPerTNLInformationList;

public class NGAP_PDUSessionResourceModifyIndicationTransfer extends NGAP_Sequence {

    public NGAP_QosFlowPerTNLInformation dLQosFlowPerTNLInformation;
    public NGAP_QosFlowPerTNLInformationList additionalDLQosFlowPerTNLInformation;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyIndicationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyIndicationTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dLQosFlowPerTNLInformation", "additionalDLQosFlowPerTNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dLQosFlowPerTNLInformation", "additionalDLQosFlowPerTNLInformation"};
    }
}
