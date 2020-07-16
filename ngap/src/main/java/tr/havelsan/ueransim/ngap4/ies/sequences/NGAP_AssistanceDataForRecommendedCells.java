package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_AssistanceDataForRecommendedCells extends NgapSequence {

    public NGAP_RecommendedCellsForPaging recommendedCellsForPaging;

    @Override
    protected String getAsnName() {
        return "AssistanceDataForRecommendedCells";
    }

    @Override
    protected String getXmlTagName() {
        return "AssistanceDataForRecommendedCells";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"recommendedCellsForPaging"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"recommendedCellsForPaging"};
    }
}
