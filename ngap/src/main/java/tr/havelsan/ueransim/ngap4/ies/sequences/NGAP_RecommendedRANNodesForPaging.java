package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_RecommendedRANNodeList;

public class NGAP_RecommendedRANNodesForPaging extends NgapSequence {

    public NGAP_RecommendedRANNodeList recommendedRANNodeList;

    @Override
    protected String getAsnName() {
        return "RecommendedRANNodesForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "RecommendedRANNodesForPaging";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"recommendedRANNodeList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"recommendedRANNodeList"};
    }
}
