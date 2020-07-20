package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_RecommendedRANNodesForPaging extends NGAP_Sequence {

    public NGAP_RecommendedRANNodeList recommendedRANNodeList;

    @Override
    public String getAsnName() {
        return "RecommendedRANNodesForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedRANNodesForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"recommendedRANNodeList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"recommendedRANNodeList"};
    }
}
