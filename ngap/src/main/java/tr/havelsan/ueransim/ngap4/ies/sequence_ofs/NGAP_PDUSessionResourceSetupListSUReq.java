package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceSetupItemSUReq;

public class NGAP_PDUSessionResourceSetupListSUReq extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemSUReq> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListSUReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListSUReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSUReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSUReq.class;
    }
}
