package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_BroadcastPLMNItem;

public class NGAP_BroadcastPLMNList extends NgapSequenceOf<NGAP_BroadcastPLMNItem> {

    @Override
    protected String getAsnName() {
        return "BroadcastPLMNList";
    }

    @Override
    protected String getXmlTagName() {
        return "BroadcastPLMNList";
    }

    @Override
    public Class<NGAP_BroadcastPLMNItem> getItemType() {
        return NGAP_BroadcastPLMNItem.class;
    }
}
