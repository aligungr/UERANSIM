package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceToReleaseItemRelCmd extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pDUSessionResourceReleaseCommandTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToReleaseItemRelCmd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToReleaseItemRelCmd";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseCommandTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseCommandTransfer"};
    }
}
