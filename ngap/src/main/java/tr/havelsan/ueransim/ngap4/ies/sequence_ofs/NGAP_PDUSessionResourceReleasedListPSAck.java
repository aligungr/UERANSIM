package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceReleasedItemPSAck;

public class NGAP_PDUSessionResourceReleasedListPSAck extends NgapSequenceOf<NGAP_PDUSessionResourceReleasedItemPSAck> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedListPSAck";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedListPSAck";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemPSAck> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemPSAck.class;
    }
}
