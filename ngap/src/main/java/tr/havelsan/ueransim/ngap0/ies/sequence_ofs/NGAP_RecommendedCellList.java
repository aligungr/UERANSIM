package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_RecommendedCellList extends NGAP_SequenceOf<NGAP_RecommendedCellItem> {

    public NGAP_RecommendedCellList() {
        super();
    }

    public NGAP_RecommendedCellList(List<NGAP_RecommendedCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RecommendedCellList";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedCellList";
    }

    @Override
    public Class<NGAP_RecommendedCellItem> getItemType() {
        return NGAP_RecommendedCellItem.class;
    }
}
