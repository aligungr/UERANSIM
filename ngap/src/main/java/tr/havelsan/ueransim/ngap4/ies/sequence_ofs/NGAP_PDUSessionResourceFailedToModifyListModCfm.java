package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToModifyItemModCfm;

public class NGAP_PDUSessionResourceFailedToModifyListModCfm extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModCfm> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModCfm.class;
    }
}
