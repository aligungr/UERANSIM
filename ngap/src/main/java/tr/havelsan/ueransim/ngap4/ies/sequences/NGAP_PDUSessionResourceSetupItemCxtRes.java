package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceSetupItemCxtRes extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pDUSessionResourceSetupResponseTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupItemCxtRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupItemCxtRes";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceSetupResponseTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceSetupResponseTransfer"};
    }
}
