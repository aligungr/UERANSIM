package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceNotifyItem;

public class NGAP_PDUSessionResourceNotifyList extends NgapSequenceOf<NGAP_PDUSessionResourceNotifyItem> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceNotifyList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceNotifyList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceNotifyItem> getItemType() {
        return NGAP_PDUSessionResourceNotifyItem.class;
    }
}
