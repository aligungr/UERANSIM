package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceModifyListModInd extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModInd> {

    public NGAP_PDUSessionResourceModifyListModInd() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModInd(List<NGAP_PDUSessionResourceModifyItemModInd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModInd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModInd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModInd> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModInd.class;
    }
}
