package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemCxtFail;

public class NGAP_PDUSessionResourceFailedToSetupListCxtFail extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtFail";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtFail> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtFail.class;
    }
}
