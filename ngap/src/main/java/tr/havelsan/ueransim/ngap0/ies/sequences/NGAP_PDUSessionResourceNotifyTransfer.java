/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowListWithCause;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowNotifyList;

public class NGAP_PDUSessionResourceNotifyTransfer extends NGAP_Sequence {

    public NGAP_QosFlowNotifyList qosFlowNotifyList;
    public NGAP_QosFlowListWithCause qosFlowReleasedList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceNotifyTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceNotifyTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"qosFlowNotifyList", "qosFlowReleasedList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"qosFlowNotifyList", "qosFlowReleasedList"};
    }
}
