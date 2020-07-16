package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AreaOfInterestItem;

public class NGAP_AreaOfInterestList extends NgapSequenceOf<NGAP_AreaOfInterestItem> {

    @Override
    protected String getAsnName() {
        return "AreaOfInterestList";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestList";
    }

    @Override
    public Class<NGAP_AreaOfInterestItem> getItemType() {
        return NGAP_AreaOfInterestItem.class;
    }
}
