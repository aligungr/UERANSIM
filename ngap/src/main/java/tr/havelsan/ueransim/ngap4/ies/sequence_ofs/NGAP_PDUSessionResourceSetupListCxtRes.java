package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceSetupItemCxtRes;

public class NGAP_PDUSessionResourceSetupListCxtRes extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemCxtRes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtRes.class;
    }
}
