package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_NAS_PDU;

public class NGAP_PDUSessionResourceSetupItemSUReq extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_NAS_PDU pDUSessionNAS_PDU;
    public NGAP_S_NSSAI s_NSSAI;
    public NgapOctetString pDUSessionResourceSetupRequestTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupItemSUReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupItemSUReq";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pDUSessionNAS-PDU", "s-NSSAI", "pDUSessionResourceSetupRequestTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pDUSessionNAS_PDU", "s_NSSAI", "pDUSessionResourceSetupRequestTransfer"};
    }
}
