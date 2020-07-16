package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceModifyItemModCfm;

public class NGAP_PDUSessionResourceModifyListModCfm extends NgapSequenceOf<NGAP_PDUSessionResourceModifyItemModCfm> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModCfm.class;
    }
}
