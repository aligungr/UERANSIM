package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_SliceSupportItem;

public class NGAP_SliceSupportList extends NgapSequenceOf<NGAP_SliceSupportItem> {

    @Override
    protected String getAsnName() {
        return "SliceSupportList";
    }

    @Override
    protected String getXmlTagName() {
        return "SliceSupportList";
    }

    @Override
    public Class<NGAP_SliceSupportItem> getItemType() {
        return NGAP_SliceSupportItem.class;
    }
}
