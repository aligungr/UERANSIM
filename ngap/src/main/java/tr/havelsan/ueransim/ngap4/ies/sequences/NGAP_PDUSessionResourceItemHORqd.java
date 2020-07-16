package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceItemHORqd extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString handoverRequiredTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceItemHORqd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceItemHORqd";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverRequiredTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverRequiredTransfer"};
    }
}
