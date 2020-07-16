package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_InfoOnRecommendedCellsAndRANNodesForPaging extends NgapSequence {

    public NGAP_RecommendedCellsForPaging recommendedCellsForPaging;
    public NGAP_RecommendedRANNodesForPaging recommendRANNodesForPaging;

    @Override
    protected String getAsnName() {
        return "InfoOnRecommendedCellsAndRANNodesForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "InfoOnRecommendedCellsAndRANNodesForPaging";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"recommendedCellsForPaging", "recommendRANNodesForPaging"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"recommendedCellsForPaging", "recommendRANNodesForPaging"};
    }
}
