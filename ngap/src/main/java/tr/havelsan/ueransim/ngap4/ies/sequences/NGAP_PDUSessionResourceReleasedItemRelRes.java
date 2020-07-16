package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceReleasedItemRelRes extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pDUSessionResourceReleaseResponseTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedItemRelRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedItemRelRes";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseResponseTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceReleaseResponseTransfer"};
    }
}
