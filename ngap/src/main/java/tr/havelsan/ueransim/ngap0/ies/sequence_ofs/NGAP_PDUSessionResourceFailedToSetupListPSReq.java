package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListPSReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemPSReq> {

    public NGAP_PDUSessionResourceFailedToSetupListPSReq() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListPSReq(List<NGAP_PDUSessionResourceFailedToSetupItemPSReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemPSReq> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemPSReq.class;
    }
}
