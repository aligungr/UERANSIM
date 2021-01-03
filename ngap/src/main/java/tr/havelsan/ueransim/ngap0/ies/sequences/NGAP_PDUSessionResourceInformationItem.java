/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_PDUSessionID;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_DRBsToQosFlowsMappingList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowInformationList;

public class NGAP_PDUSessionResourceInformationItem extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_QosFlowInformationList qosFlowInformationList;
    public NGAP_DRBsToQosFlowsMappingList dRBsToQosFlowsMappingList;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "qosFlowInformationList", "dRBsToQosFlowsMappingList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "qosFlowInformationList", "dRBsToQosFlowsMappingList"};
    }
}
