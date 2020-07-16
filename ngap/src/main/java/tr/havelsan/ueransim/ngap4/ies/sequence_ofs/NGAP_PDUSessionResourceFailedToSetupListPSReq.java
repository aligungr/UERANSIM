package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemPSReq;

public class NGAP_PDUSessionResourceFailedToSetupListPSReq extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemPSReq> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemPSReq> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemPSReq.class;
    }
}
