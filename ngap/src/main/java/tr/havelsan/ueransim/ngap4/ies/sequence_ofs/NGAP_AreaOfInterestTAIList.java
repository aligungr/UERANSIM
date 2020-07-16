package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AreaOfInterestTAIItem;

public class NGAP_AreaOfInterestTAIList extends NgapSequenceOf<NGAP_AreaOfInterestTAIItem> {

    @Override
    protected String getAsnName() {
        return "AreaOfInterestTAIList";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestTAIList";
    }

    @Override
    public Class<NGAP_AreaOfInterestTAIItem> getItemType() {
        return NGAP_AreaOfInterestTAIItem.class;
    }
}
