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
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_QosFlowToBeForwardedList;

public class NGAP_HandoverCommandTransfer extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_QosFlowToBeForwardedList qosFlowToBeForwardedList;
    public NGAP_DataForwardingResponseDRBList dataForwardingResponseDRBList;

    @Override
    public String getAsnName() {
        return "HandoverCommandTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "HandoverCommandTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dLForwardingUP-TNLInformation", "qosFlowToBeForwardedList", "dataForwardingResponseDRBList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dLForwardingUP_TNLInformation", "qosFlowToBeForwardedList", "dataForwardingResponseDRBList"};
    }
}
