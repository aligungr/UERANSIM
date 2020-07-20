package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceListHORqd extends NGAP_SequenceOf<NGAP_PDUSessionResourceItemHORqd> {

    public NGAP_PDUSessionResourceListHORqd() {
        super();
    }

    public NGAP_PDUSessionResourceListHORqd(List<NGAP_PDUSessionResourceItemHORqd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemHORqd> getItemType() {
        return NGAP_PDUSessionResourceItemHORqd.class;
    }
}
