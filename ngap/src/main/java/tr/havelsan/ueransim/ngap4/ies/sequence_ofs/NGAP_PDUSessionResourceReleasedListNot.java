package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceReleasedItemNot;

public class NGAP_PDUSessionResourceReleasedListNot extends NgapSequenceOf<NGAP_PDUSessionResourceReleasedItemNot> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedListNot";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedListNot";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemNot> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemNot.class;
    }
}
