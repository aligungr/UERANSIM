package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemCxtRes;

public class NGAP_PDUSessionResourceFailedToSetupListCxtRes extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupListCxtRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemCxtRes.class;
    }
}
