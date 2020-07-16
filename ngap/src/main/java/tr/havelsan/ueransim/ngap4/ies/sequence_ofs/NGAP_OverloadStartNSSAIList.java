package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_OverloadStartNSSAIItem;

public class NGAP_OverloadStartNSSAIList extends NgapSequenceOf<NGAP_OverloadStartNSSAIItem> {

    @Override
    protected String getAsnName() {
        return "OverloadStartNSSAIList";
    }

    @Override
    protected String getXmlTagName() {
        return "OverloadStartNSSAIList";
    }

    @Override
    public Class<NGAP_OverloadStartNSSAIItem> getItemType() {
        return NGAP_OverloadStartNSSAIItem.class;
    }
}
