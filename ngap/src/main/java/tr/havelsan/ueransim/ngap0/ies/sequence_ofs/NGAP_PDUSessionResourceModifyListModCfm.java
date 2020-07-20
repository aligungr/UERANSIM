package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModCfm extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModCfm> {

    public NGAP_PDUSessionResourceModifyListModCfm() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModCfm(List<NGAP_PDUSessionResourceModifyItemModCfm> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModCfm.class;
    }
}
