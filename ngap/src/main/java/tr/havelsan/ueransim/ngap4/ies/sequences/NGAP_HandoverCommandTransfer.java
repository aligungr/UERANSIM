package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

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
