package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapProtocolIeContainer;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_PDUSessionResourceSetupRequestTransfer extends NgapSequence {

    public NgapProtocolIeContainer protocolIEs;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupRequestTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupRequestTransfer";
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
