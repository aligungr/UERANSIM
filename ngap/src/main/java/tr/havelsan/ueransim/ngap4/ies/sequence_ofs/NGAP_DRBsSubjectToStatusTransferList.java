package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DRBsSubjectToStatusTransferItem;

public class NGAP_DRBsSubjectToStatusTransferList extends NgapSequenceOf<NGAP_DRBsSubjectToStatusTransferItem> {

    @Override
    protected String getAsnName() {
        return "DRBsSubjectToStatusTransferList";
    }

    @Override
    protected String getXmlTagName() {
        return "DRBsSubjectToStatusTransferList";
    }

    @Override
    public Class<NGAP_DRBsSubjectToStatusTransferItem> getItemType() {
        return NGAP_DRBsSubjectToStatusTransferItem.class;
    }
}
