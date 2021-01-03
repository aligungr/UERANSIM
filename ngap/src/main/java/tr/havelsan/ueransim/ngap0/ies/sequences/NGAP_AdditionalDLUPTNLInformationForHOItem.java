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
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowListWithDataForwarding;

public class NGAP_AdditionalDLUPTNLInformationForHOItem extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation additionalDL_NGU_UP_TNLInformation;
    public NGAP_QosFlowListWithDataForwarding additionalQosFlowSetupResponseList;
    public NGAP_UPTransportLayerInformation additionalDLForwardingUPTNLInformation;

    @Override
    public String getAsnName() {
        return "AdditionalDLUPTNLInformationForHOItem";
    }

    @Override
    public String getXmlTagName() {
        return "AdditionalDLUPTNLInformationForHOItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"additionalDL-NGU-UP-TNLInformation", "additionalQosFlowSetupResponseList", "additionalDLForwardingUPTNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"additionalDL_NGU_UP_TNLInformation", "additionalQosFlowSetupResponseList", "additionalDLForwardingUPTNLInformation"};
    }
}
