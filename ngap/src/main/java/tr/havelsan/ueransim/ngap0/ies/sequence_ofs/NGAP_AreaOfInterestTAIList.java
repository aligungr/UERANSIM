package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_AreaOfInterestTAIList extends NGAP_SequenceOf<NGAP_AreaOfInterestTAIItem> {

    public NGAP_AreaOfInterestTAIList() {
        super();
    }

    public NGAP_AreaOfInterestTAIList(List<NGAP_AreaOfInterestTAIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AreaOfInterestTAIList";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestTAIList";
    }

    @Override
    public Class<NGAP_AreaOfInterestTAIItem> getItemType() {
        return NGAP_AreaOfInterestTAIItem.class;
    }
}
