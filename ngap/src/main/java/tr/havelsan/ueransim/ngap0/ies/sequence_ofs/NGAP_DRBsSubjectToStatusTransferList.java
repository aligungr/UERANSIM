package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_DRBsSubjectToStatusTransferList extends NGAP_SequenceOf<NGAP_DRBsSubjectToStatusTransferItem> {

    public NGAP_DRBsSubjectToStatusTransferList() {
        super();
    }

    public NGAP_DRBsSubjectToStatusTransferList(List<NGAP_DRBsSubjectToStatusTransferItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "DRBsSubjectToStatusTransferList";
    }

    @Override
    public String getXmlTagName() {
        return "DRBsSubjectToStatusTransferList";
    }

    @Override
    public Class<NGAP_DRBsSubjectToStatusTransferItem> getItemType() {
        return NGAP_DRBsSubjectToStatusTransferItem.class;
    }
}
