package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceFailedToSetupItemHOAck extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString handoverResourceAllocationUnsuccessfulTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupItemHOAck";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupItemHOAck";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverResourceAllocationUnsuccessfulTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverResourceAllocationUnsuccessfulTransfer"};
    }
}
