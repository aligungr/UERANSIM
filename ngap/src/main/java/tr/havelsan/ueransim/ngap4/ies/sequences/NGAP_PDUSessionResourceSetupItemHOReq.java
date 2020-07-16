package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;

public class NGAP_PDUSessionResourceSetupItemHOReq extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_S_NSSAI s_NSSAI;
    public NgapOctetString handoverRequestTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupItemHOReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupItemHOReq";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "s-NSSAI", "handoverRequestTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "s_NSSAI", "handoverRequestTransfer"};
    }
}
