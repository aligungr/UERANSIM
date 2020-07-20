package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_RecommendedRANNodeList extends NGAP_SequenceOf<NGAP_RecommendedRANNodeItem> {

    public NGAP_RecommendedRANNodeList() {
        super();
    }

    public NGAP_RecommendedRANNodeList(List<NGAP_RecommendedRANNodeItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public Class<NGAP_RecommendedRANNodeItem> getItemType() {
        return NGAP_RecommendedRANNodeItem.class;
    }
}
