package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_InfoOnRecommendedCellsAndRANNodesForPaging extends NGAP_Sequence {

    public NGAP_RecommendedCellsForPaging recommendedCellsForPaging;
    public NGAP_RecommendedRANNodesForPaging recommendRANNodesForPaging;

    @Override
    public String getAsnName() {
        return "InfoOnRecommendedCellsAndRANNodesForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "InfoOnRecommendedCellsAndRANNodesForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"recommendedCellsForPaging", "recommendRANNodesForPaging"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"recommendedCellsForPaging", "recommendRANNodesForPaging"};
    }
}
