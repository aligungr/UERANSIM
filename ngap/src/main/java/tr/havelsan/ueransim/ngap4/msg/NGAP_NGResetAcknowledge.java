package tr.havelsan.ueransim.ngap4.msg;

import tr.havelsan.ueransim.ngap4.pdu.*;
import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap2.NgapMessageType;

public class NGAP_NGResetAcknowledge extends NGAP_Sequence {

    public NGAP_ProtocolIEContainer protocolIEs;

    public NGAP_NGResetAcknowledge() {

    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"protocolIEs"};
    }


    @Override
    public String[] getMemberNames() {
        return new String[]{"protocolIEs"};
    }

    @Override
    public String getAsnName() {
        return "NGResetAcknowledge";
    }

    @Override
    public String getXmlTagName() {
        return "NGResetAcknowledge";
    }

}
