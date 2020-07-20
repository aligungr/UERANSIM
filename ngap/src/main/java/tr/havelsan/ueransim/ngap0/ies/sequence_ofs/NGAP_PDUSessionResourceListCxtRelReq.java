package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceListCxtRelReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceItemCxtRelReq> {

    public NGAP_PDUSessionResourceListCxtRelReq() {
        super();
    }

    public NGAP_PDUSessionResourceListCxtRelReq(List<NGAP_PDUSessionResourceItemCxtRelReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelReq> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelReq.class;
    }
}
