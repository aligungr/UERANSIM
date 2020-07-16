package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceReleasedItemRelRes;

public class NGAP_PDUSessionResourceReleasedListRelRes extends NgapSequenceOf<NGAP_PDUSessionResourceReleasedItemRelRes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedListRelRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedListRelRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemRelRes> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemRelRes.class;
    }
}
