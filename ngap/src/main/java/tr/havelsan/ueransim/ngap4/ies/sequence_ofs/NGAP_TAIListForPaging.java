package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TAIListForPagingItem;

public class NGAP_TAIListForPaging extends NgapSequenceOf<NGAP_TAIListForPagingItem> {

    @Override
    protected String getAsnName() {
        return "TAIListForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForPaging";
    }

    @Override
    public Class<NGAP_TAIListForPagingItem> getItemType() {
        return NGAP_TAIListForPagingItem.class;
    }
}
