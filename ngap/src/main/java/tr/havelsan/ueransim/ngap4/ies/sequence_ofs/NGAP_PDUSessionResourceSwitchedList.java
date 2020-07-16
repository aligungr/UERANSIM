package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceSwitchedItem;

public class NGAP_PDUSessionResourceSwitchedList extends NgapSequenceOf<NGAP_PDUSessionResourceSwitchedItem> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSwitchedList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSwitchedList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSwitchedItem> getItemType() {
        return NGAP_PDUSessionResourceSwitchedItem.class;
    }
}
