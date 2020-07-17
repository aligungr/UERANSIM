package tr.havelsan.ueransim.ngap4.msg;

import tr.havelsan.ueransim.ngap4.pdu.*;
import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap2.NgapMessageType;

public class NGAP_PDUSessionResourceModifyConfirm extends NGAP_Sequence {

    public NGAP_ProtocolIEContainer protocolIEs;

    public NGAP_PDUSessionResourceModifyConfirm() {

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
        return "PDUSessionResourceModifyConfirm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyConfirm";
    }

}
