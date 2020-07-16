package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceFailedToSetupItemSURes;

public class NGAP_PDUSessionResourceFailedToSetupListSURes extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemSURes> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemSURes.class;
    }
}
