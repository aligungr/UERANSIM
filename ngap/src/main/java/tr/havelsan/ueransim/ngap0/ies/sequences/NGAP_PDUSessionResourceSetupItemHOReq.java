package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;

public class NGAP_PDUSessionResourceSetupItemHOReq extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_S_NSSAI s_NSSAI;
    public NGAP_OctetString handoverRequestTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupItemHOReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupItemHOReq";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "s-NSSAI", "handoverRequestTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "s_NSSAI", "handoverRequestTransfer"};
    }
}
