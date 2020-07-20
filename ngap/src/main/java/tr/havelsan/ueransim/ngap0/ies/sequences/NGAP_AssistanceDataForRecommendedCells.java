package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_AssistanceDataForRecommendedCells extends NGAP_Sequence {

    public NGAP_RecommendedCellsForPaging recommendedCellsForPaging;

    @Override
    public String getAsnName() {
        return "AssistanceDataForRecommendedCells";
    }

    @Override
    public String getXmlTagName() {
        return "AssistanceDataForRecommendedCells";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"recommendedCellsForPaging"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"recommendedCellsForPaging"};
    }
}
