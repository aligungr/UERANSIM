package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;

public class NGAP_PDUSessionResourceModifyRequestTransfer extends NGAP_Sequence {

    public NGAP_ProtocolIEContainer protocolIEs;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyRequestTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyRequestTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"protocolIEs"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"protocolIEs"};
    }
}
