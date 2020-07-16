package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceSetupItemSURes;

public class NGAP_PDUSessionResourceSetupListSURes extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemSURes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSURes.class;
    }
}
