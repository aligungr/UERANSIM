package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceToReleaseItemRelCmd;

public class NGAP_PDUSessionResourceToReleaseListRelCmd extends NgapSequenceOf<NGAP_PDUSessionResourceToReleaseItemRelCmd> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemRelCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemRelCmd.class;
    }
}
