package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceSetupItemHOReq;

public class NGAP_PDUSessionResourceSetupListHOReq extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemHOReq> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListHOReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListHOReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemHOReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemHOReq.class;
    }
}
