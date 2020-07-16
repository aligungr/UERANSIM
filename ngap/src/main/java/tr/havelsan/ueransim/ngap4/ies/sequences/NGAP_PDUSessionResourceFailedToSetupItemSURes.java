package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceFailedToSetupItemSURes extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pDUSessionResourceSetupUnsuccessfulTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupItemSURes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupItemSURes";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceSetupUnsuccessfulTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceSetupUnsuccessfulTransfer"};
    }
}
