package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceSwitchedItem extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pathSwitchRequestAcknowledgeTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSwitchedItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSwitchedItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestAcknowledgeTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestAcknowledgeTransfer"};
    }
}
