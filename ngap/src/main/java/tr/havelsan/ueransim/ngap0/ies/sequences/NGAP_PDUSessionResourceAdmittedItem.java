package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceAdmittedItem extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString handoverRequestAcknowledgeTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceAdmittedItem";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceAdmittedItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverRequestAcknowledgeTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverRequestAcknowledgeTransfer"};
    }
}
