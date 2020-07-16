package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceAdmittedItem extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString handoverRequestAcknowledgeTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceAdmittedItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceAdmittedItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverRequestAcknowledgeTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverRequestAcknowledgeTransfer"};
    }
}
