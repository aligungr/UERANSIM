package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceToBeSwitchedDLItem;

public class NGAP_PDUSessionResourceToBeSwitchedDLList extends NgapSequenceOf<NGAP_PDUSessionResourceToBeSwitchedDLItem> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToBeSwitchedDLList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToBeSwitchedDLList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToBeSwitchedDLItem> getItemType() {
        return NGAP_PDUSessionResourceToBeSwitchedDLItem.class;
    }
}
