package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceAdmittedItem;

public class NGAP_PDUSessionResourceAdmittedList extends NgapSequenceOf<NGAP_PDUSessionResourceAdmittedItem> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceAdmittedList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceAdmittedList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceAdmittedItem> getItemType() {
        return NGAP_PDUSessionResourceAdmittedItem.class;
    }
}
