package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceSetupListCxtReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemCxtReq> {

    public NGAP_PDUSessionResourceSetupListCxtReq() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListCxtReq(List<NGAP_PDUSessionResourceSetupItemCxtReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListCxtReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtReq.class;
    }
}
