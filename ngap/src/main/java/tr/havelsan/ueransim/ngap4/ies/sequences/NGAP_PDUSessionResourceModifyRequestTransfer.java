package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapProtocolIeContainer;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_PDUSessionResourceModifyRequestTransfer extends NgapSequence {

    public NgapProtocolIeContainer protocolIEs;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyRequestTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyRequestTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"protocolIEs"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"protocolIEs"};
    }
}
