package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_OverloadStartNSSAIList extends NGAP_SequenceOf<NGAP_OverloadStartNSSAIItem> {

    public NGAP_OverloadStartNSSAIList() {
        super();
    }

    public NGAP_OverloadStartNSSAIList(List<NGAP_OverloadStartNSSAIItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "OverloadStartNSSAIList";
    }

    @Override
    public String getXmlTagName() {
        return "OverloadStartNSSAIList";
    }

    @Override
    public Class<NGAP_OverloadStartNSSAIItem> getItemType() {
        return NGAP_OverloadStartNSSAIItem.class;
    }
}
