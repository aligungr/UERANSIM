package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceFailedToSetupItemHOAck extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString handoverResourceAllocationUnsuccessfulTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupItemHOAck";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupItemHOAck";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverResourceAllocationUnsuccessfulTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverResourceAllocationUnsuccessfulTransfer"};
    }
}
