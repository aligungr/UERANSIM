package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModRes> {

    public NGAP_PDUSessionResourceModifyListModRes() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModRes(List<NGAP_PDUSessionResourceModifyItemModRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModRes.class;
    }
}
