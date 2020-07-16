package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_RecommendedCellItem;

public class NGAP_RecommendedCellList extends NgapSequenceOf<NGAP_RecommendedCellItem> {

    @Override
    protected String getAsnName() {
        return "RecommendedCellList";
    }

    @Override
    protected String getXmlTagName() {
        return "RecommendedCellList";
    }

    @Override
    public Class<NGAP_RecommendedCellItem> getItemType() {
        return NGAP_RecommendedCellItem.class;
    }
}
