package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListCxtFail extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> {

    public NGAP_PDUSessionResourceFailedToSetupListCxtFail() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListCxtFail(List<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtFail.class;
    }
}
