package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceReleasedListPSAck extends NGAP_SequenceOf<NGAP_PDUSessionResourceReleasedItemPSAck> {

    public NGAP_PDUSessionResourceReleasedListPSAck() {
        super();
    }

    public NGAP_PDUSessionResourceReleasedListPSAck(List<NGAP_PDUSessionResourceReleasedItemPSAck> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedListPSAck";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedListPSAck";
    }

    @Override
    public Class<NGAP_PDUSessionResourceReleasedItemPSAck> getItemType() {
        return NGAP_PDUSessionResourceReleasedItemPSAck.class;
    }
}
