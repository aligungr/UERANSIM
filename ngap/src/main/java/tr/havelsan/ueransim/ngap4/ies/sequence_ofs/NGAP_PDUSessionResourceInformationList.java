package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceInformationItem;

public class NGAP_PDUSessionResourceInformationList extends NgapSequenceOf<NGAP_PDUSessionResourceInformationItem> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceInformationItem> getItemType() {
        return NGAP_PDUSessionResourceInformationItem.class;
    }
}
