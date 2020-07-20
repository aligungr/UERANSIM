package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_PDUSessionResourceToReleaseListRelCmd extends NGAP_SequenceOf<NGAP_PDUSessionResourceToReleaseItemRelCmd> {

    public NGAP_PDUSessionResourceToReleaseListRelCmd() {
        super();
    }

    public NGAP_PDUSessionResourceToReleaseListRelCmd(List<NGAP_PDUSessionResourceToReleaseItemRelCmd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemRelCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemRelCmd.class;
    }
}
