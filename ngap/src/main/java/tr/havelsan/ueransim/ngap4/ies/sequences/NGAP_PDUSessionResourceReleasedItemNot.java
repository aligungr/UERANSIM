package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceReleasedItemNot extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pDUSessionResourceNotifyReleasedTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedItemNot";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedItemNot";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceNotifyReleasedTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceNotifyReleasedTransfer"};
    }
}
