package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_RecommendedRANNodeItem;

public class NGAP_RecommendedRANNodeList extends NgapSequenceOf<NGAP_RecommendedRANNodeItem> {

    @Override
    protected String getAsnName() {
        return "RecommendedRANNodeList";
    }

    @Override
    protected String getXmlTagName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public Class<NGAP_RecommendedRANNodeItem> getItemType() {
        return NGAP_RecommendedRANNodeItem.class;
    }
}
