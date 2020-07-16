package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceToReleaseItemHOCmd extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString handoverPreparationUnsuccessfulTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToReleaseItemHOCmd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToReleaseItemHOCmd";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverPreparationUnsuccessfulTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverPreparationUnsuccessfulTransfer"};
    }
}
