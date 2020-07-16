package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceItemCxtRelReq;

public class NGAP_PDUSessionResourceListCxtRelReq extends NgapSequenceOf<NGAP_PDUSessionResourceItemCxtRelReq> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelReq> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelReq.class;
    }
}
