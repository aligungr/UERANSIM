package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_SliceOverloadItem;

public class NGAP_SliceOverloadList extends NgapSequenceOf<NGAP_SliceOverloadItem> {

    @Override
    protected String getAsnName() {
        return "SliceOverloadList";
    }

    @Override
    protected String getXmlTagName() {
        return "SliceOverloadList";
    }

    @Override
    public Class<NGAP_SliceOverloadItem> getItemType() {
        return NGAP_SliceOverloadItem.class;
    }
}
