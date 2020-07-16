package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AreaOfInterestCellItem;

public class NGAP_AreaOfInterestCellList extends NgapSequenceOf<NGAP_AreaOfInterestCellItem> {

    @Override
    protected String getAsnName() {
        return "AreaOfInterestCellList";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestCellList";
    }

    @Override
    public Class<NGAP_AreaOfInterestCellItem> getItemType() {
        return NGAP_AreaOfInterestCellItem.class;
    }
}
