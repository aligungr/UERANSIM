package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceReleasedListNot extends NGAP_SequenceOf<NGAP_PDUSessionResourceReleasedItemNot> {

    public NGAP_PDUSessionResourceReleasedListNot() {
        super();
    }

    public NGAP_PDUSessionResourceReleasedListNot(List<NGAP_PDUSessionResourceReleasedItemNot> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedListNot";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedListNot";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemNot> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemNot.class;
    }
}
