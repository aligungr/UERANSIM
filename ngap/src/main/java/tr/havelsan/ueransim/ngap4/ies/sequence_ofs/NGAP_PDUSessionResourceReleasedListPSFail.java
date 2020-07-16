package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceReleasedItemPSFail;

public class NGAP_PDUSessionResourceReleasedListPSFail extends NgapSequenceOf<NGAP_PDUSessionResourceReleasedItemPSFail> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleasedListPSFail";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleasedListPSFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemPSFail> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemPSFail.class;
    }
}
