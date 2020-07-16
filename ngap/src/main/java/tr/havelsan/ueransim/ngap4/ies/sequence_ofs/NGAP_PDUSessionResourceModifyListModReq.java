package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceModifyItemModReq;

public class NGAP_PDUSessionResourceModifyListModReq extends NgapSequenceOf<NGAP_PDUSessionResourceModifyItemModReq> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModReq> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModReq.class;
    }
}
