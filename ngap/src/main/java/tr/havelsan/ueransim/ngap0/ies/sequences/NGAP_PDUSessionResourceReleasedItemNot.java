package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceReleasedItemNot extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pDUSessionResourceNotifyReleasedTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedItemNot";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedItemNot";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceNotifyReleasedTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceNotifyReleasedTransfer"};
    }
}
