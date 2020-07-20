package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;

public class NGAP_PDUSessionResourceSetupRequestTransfer extends NGAP_Sequence {

    public NGAP_ProtocolIEContainer protocolIEs;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupRequestTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupRequestTransfer";
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
