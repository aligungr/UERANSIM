package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModReq> {

    public NGAP_PDUSessionResourceModifyListModReq() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModReq(List<NGAP_PDUSessionResourceModifyItemModReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModReq> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModReq.class;
    }
}
