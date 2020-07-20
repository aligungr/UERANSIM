package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceSwitchedItem extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pathSwitchRequestAcknowledgeTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSwitchedItem";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSwitchedItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestAcknowledgeTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestAcknowledgeTransfer"};
    }
}
