package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceModifyItemModInd;

public class NGAP_PDUSessionResourceModifyListModInd extends NgapSequenceOf<NGAP_PDUSessionResourceModifyItemModInd> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyListModInd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyListModInd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModInd> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModInd.class;
    }
}
