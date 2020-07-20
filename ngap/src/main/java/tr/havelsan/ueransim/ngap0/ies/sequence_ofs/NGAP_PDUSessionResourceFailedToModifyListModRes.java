package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToModifyListModRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModRes> {

    public NGAP_PDUSessionResourceFailedToModifyListModRes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToModifyListModRes(List<NGAP_PDUSessionResourceFailedToModifyItemModRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModRes.class;
    }
}
