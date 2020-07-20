package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToModifyListModCfm extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModCfm> {

    public NGAP_PDUSessionResourceFailedToModifyListModCfm() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToModifyListModCfm(List<NGAP_PDUSessionResourceFailedToModifyItemModCfm> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModCfm.class;
    }
}
