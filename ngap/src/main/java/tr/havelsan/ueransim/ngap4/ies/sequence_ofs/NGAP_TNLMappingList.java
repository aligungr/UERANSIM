package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TNLMappingItem;

public class NGAP_TNLMappingList extends NgapSequenceOf<NGAP_TNLMappingItem> {

    @Override
    protected String getAsnName() {
        return "TNLMappingList";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLMappingList";
    }

    @Override
    public Class<NGAP_TNLMappingItem> getItemType() {
        return NGAP_TNLMappingItem.class;
    }
}
