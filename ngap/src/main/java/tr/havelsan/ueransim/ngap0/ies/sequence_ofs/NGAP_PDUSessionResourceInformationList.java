package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceInformationList extends NGAP_SequenceOf<NGAP_PDUSessionResourceInformationItem> {

    public NGAP_PDUSessionResourceInformationList() {
        super();
    }

    public NGAP_PDUSessionResourceInformationList(List<NGAP_PDUSessionResourceInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceInformationList";
    }

    @Override
    public Class<NGAP_PDUSessionResourceInformationItem> getItemType() {
        return NGAP_PDUSessionResourceInformationItem.class;
    }
}
