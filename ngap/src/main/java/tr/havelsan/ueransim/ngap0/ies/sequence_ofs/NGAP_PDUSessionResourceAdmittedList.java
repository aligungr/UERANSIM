package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceAdmittedList extends NGAP_SequenceOf<NGAP_PDUSessionResourceAdmittedItem> {

    public NGAP_PDUSessionResourceAdmittedList() {
        super();
    }

    public NGAP_PDUSessionResourceAdmittedList(List<NGAP_PDUSessionResourceAdmittedItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceAdmittedList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceAdmittedList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceAdmittedItem> getItemType() {
        return NGAP_PDUSessionResourceAdmittedItem.class;
    }
}
