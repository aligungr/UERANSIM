package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_SupportedTAItem;

public class NGAP_SupportedTAList extends NgapSequenceOf<NGAP_SupportedTAItem> {

    @Override
    protected String getAsnName() {
        return "SupportedTAList";
    }

    @Override
    protected String getXmlTagName() {
        return "SupportedTAList";
    }

    @Override
    public Class<NGAP_SupportedTAItem> getItemType() {
        return NGAP_SupportedTAItem.class;
    }
}
