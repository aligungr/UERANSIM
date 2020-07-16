package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;
import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PDUSessionID;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_NAS_PDU;

public class NGAP_PDUSessionResourceModifyItemModReq extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_NAS_PDU nAS_PDU;
    public NgapOctetString pDUSessionResourceModifyRequestTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyItemModReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyItemModReq";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "nAS-PDU", "pDUSessionResourceModifyRequestTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "nAS_PDU", "pDUSessionResourceModifyRequestTransfer"};
    }
}
