package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_RecommendedCellList;

public class NGAP_RecommendedCellsForPaging extends NgapSequence {

    public NGAP_RecommendedCellList recommendedCellList;

    @Override
    protected String getAsnName() {
        return "RecommendedCellsForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "RecommendedCellsForPaging";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"recommendedCellList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"recommendedCellList"};
    }
}
