package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceReleasedListPSFail extends NGAP_SequenceOf<NGAP_PDUSessionResourceReleasedItemPSFail> {

    public NGAP_PDUSessionResourceReleasedListPSFail() {
        super();
    }

    public NGAP_PDUSessionResourceReleasedListPSFail(List<NGAP_PDUSessionResourceReleasedItemPSFail> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedListPSFail";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedListPSFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemPSFail> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemPSFail.class;
    }
}
