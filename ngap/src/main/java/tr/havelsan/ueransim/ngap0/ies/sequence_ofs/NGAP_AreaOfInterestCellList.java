package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_AreaOfInterestCellList extends NGAP_SequenceOf<NGAP_AreaOfInterestCellItem> {

    public NGAP_AreaOfInterestCellList() {
        super();
    }

    public NGAP_AreaOfInterestCellList(List<NGAP_AreaOfInterestCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestCellList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestCellList";
    }

    @Override
    public Class<NGAP_AreaOfInterestCellItem> getItemType() {
        return NGAP_AreaOfInterestCellItem.class;
    }
}
