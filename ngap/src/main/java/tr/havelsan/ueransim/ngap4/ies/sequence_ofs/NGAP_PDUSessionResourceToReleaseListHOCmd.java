package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceToReleaseItemHOCmd;

public class NGAP_PDUSessionResourceToReleaseListHOCmd extends NgapSequenceOf<NGAP_PDUSessionResourceToReleaseItemHOCmd> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceToReleaseListHOCmd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceToReleaseListHOCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemHOCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemHOCmd.class;
    }
}
