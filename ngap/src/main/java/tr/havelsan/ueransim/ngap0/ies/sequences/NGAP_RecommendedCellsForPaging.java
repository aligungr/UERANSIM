package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_RecommendedCellsForPaging extends NGAP_Sequence {

    public NGAP_RecommendedCellList recommendedCellList;

    @Override
    public String getAsnName() {
        return "RecommendedCellsForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedCellsForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"recommendedCellList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"recommendedCellList"};
    }
}
