package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListSUReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemSUReq> {

    public NGAP_PDUSessionResourceSetupListSUReq() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListSUReq(List<NGAP_PDUSessionResourceSetupItemSUReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListSUReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListSUReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSUReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSUReq.class;
    }
}
