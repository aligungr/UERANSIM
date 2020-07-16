package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_AreaOfInterestRANNodeItem;

public class NGAP_AreaOfInterestRANNodeList extends NgapSequenceOf<NGAP_AreaOfInterestRANNodeItem> {

    @Override
    protected String getAsnName() {
        return "AreaOfInterestRANNodeList";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterestRANNodeList";
    }

    @Override
    public Class<NGAP_AreaOfInterestRANNodeItem> getItemType() {
        return NGAP_AreaOfInterestRANNodeItem.class;
    }
}
