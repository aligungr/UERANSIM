package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToModifyItemModRes;

public class NGAP_PDUSessionResourceFailedToModifyListModRes extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModRes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModRes.class;
    }
}
