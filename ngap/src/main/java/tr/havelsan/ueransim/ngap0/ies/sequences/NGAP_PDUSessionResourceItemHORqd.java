package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceItemHORqd extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString handoverRequiredTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceItemHORqd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceItemHORqd";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverRequiredTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverRequiredTransfer"};
    }
}
