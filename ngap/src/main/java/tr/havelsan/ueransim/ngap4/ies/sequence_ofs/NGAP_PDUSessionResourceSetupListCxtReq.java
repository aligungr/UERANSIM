package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceSetupItemCxtReq;

public class NGAP_PDUSessionResourceSetupListCxtReq extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemCxtReq> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListCxtReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtReq.class;
    }
}
