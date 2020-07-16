package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_DataForwardingResponseDRBList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_QosFlowToBeForwardedList;

public class NGAP_HandoverCommandTransfer extends NgapSequence {

    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_QosFlowToBeForwardedList qosFlowToBeForwardedList;
    public NGAP_DataForwardingResponseDRBList dataForwardingResponseDRBList;

    @Override
    protected String getAsnName() {
        return "HandoverCommandTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "HandoverCommandTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dLForwardingUP-TNLInformation", "qosFlowToBeForwardedList", "dataForwardingResponseDRBList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dLForwardingUP_TNLInformation", "qosFlowToBeForwardedList", "dataForwardingResponseDRBList"};
    }
}
