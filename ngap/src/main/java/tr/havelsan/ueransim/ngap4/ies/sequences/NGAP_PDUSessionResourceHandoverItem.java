package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceHandoverItem extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString handoverCommandTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceHandoverItem";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceHandoverItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverCommandTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverCommandTransfer"};
    }
}
