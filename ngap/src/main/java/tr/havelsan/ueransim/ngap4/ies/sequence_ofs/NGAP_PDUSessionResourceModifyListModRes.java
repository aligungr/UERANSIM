package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceModifyItemModRes;

public class NGAP_PDUSessionResourceModifyListModRes extends NgapSequenceOf<NGAP_PDUSessionResourceModifyItemModRes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModRes.class;
    }
}
