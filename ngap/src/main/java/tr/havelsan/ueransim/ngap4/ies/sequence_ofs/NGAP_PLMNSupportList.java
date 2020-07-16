package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PLMNSupportItem;

public class NGAP_PLMNSupportList extends NgapSequenceOf<NGAP_PLMNSupportItem> {

    @Override
    protected String getAsnName() {
        return "PLMNSupportList";
    }

    @Override
    protected String getXmlTagName() {
        return "PLMNSupportList";
    }

    @Override
    public Class<NGAP_PLMNSupportItem> getItemType() {
        return NGAP_PLMNSupportItem.class;
    }
}
