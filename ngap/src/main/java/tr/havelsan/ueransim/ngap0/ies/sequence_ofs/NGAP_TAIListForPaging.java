package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_TAIListForPaging extends NGAP_SequenceOf<NGAP_TAIListForPagingItem> {

    public NGAP_TAIListForPaging() {
        super();
    }

    public NGAP_TAIListForPaging(List<NGAP_TAIListForPagingItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIListForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForPaging";
    }

    @Override
    public Class<NGAP_TAIListForPagingItem> getItemType() {
        return NGAP_TAIListForPagingItem.class;
    }
}
