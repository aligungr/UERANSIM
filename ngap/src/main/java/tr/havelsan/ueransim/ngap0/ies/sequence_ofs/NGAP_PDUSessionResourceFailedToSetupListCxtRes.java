package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceFailedToSetupListCxtRes extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> {

    public NGAP_PDUSessionResourceFailedToSetupListCxtRes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListCxtRes(List<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtRes.class;
    }
}
