package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceToReleaseListHOCmd extends NGAP_SequenceOf<NGAP_PDUSessionResourceToReleaseItemHOCmd> {

    public NGAP_PDUSessionResourceToReleaseListHOCmd() {
        super();
    }

    public NGAP_PDUSessionResourceToReleaseListHOCmd(List<NGAP_PDUSessionResourceToReleaseItemHOCmd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseListHOCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseListHOCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemHOCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemHOCmd.class;
    }
}
