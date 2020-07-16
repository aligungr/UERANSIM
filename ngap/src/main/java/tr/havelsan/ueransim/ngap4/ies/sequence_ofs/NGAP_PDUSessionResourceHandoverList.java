package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceHandoverItem;

public class NGAP_PDUSessionResourceHandoverList extends NgapSequenceOf<NGAP_PDUSessionResourceHandoverItem> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceHandoverList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceHandoverList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceHandoverItem> getItemType() {
        return NGAP_PDUSessionResourceHandoverItem.class;
    }
}
