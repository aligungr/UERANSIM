package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceFailedToModifyItemModRes extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString pDUSessionResourceModifyUnsuccessfulTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToModifyItemModRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyItemModRes";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyUnsuccessfulTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionResourceModifyUnsuccessfulTransfer"};
    }
}
