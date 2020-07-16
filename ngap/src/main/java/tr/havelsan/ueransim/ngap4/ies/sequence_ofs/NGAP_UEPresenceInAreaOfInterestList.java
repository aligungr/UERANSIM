package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UEPresenceInAreaOfInterestItem;

public class NGAP_UEPresenceInAreaOfInterestList extends NgapSequenceOf<NGAP_UEPresenceInAreaOfInterestItem> {

    @Override
    protected String getAsnName() {
        return "UEPresenceInAreaOfInterestList";
    }

    @Override
    protected String getXmlTagName() {
        return "UEPresenceInAreaOfInterestList";
    }

    @Override
    public Class<NGAP_UEPresenceInAreaOfInterestItem> getItemType() {
        return NGAP_UEPresenceInAreaOfInterestItem.class;
    }
}
