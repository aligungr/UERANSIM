package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_BroadcastPLMNList extends NGAP_SequenceOf<NGAP_BroadcastPLMNItem> {

    public NGAP_BroadcastPLMNList() {
        super();
    }

    public NGAP_BroadcastPLMNList(List<NGAP_BroadcastPLMNItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "BroadcastPLMNList";
    }

    @Override
    public String getXmlTagName() {
        return "BroadcastPLMNList";
    }

    @Override
    public Class<NGAP_BroadcastPLMNItem> getItemType() {
        return NGAP_BroadcastPLMNItem.class;
    }
}
