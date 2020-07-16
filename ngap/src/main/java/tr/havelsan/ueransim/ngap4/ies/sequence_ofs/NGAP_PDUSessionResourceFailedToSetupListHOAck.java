package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemHOAck;

public class NGAP_PDUSessionResourceFailedToSetupListHOAck extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemHOAck> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupListHOAck";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListHOAck";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemHOAck> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemHOAck.class;
    }
}
