package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAIListForInactiveItem;

public class NGAP_TAIListForInactive extends NgapSequenceOf<NGAP_TAIListForInactiveItem> {

    @Override
    protected String getAsnName() {
        return "TAIListForInactive";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForInactive";
    }

    @Override
    public Class<NGAP_TAIListForInactiveItem> getItemType() {
        return NGAP_TAIListForInactiveItem.class;
    }
}
