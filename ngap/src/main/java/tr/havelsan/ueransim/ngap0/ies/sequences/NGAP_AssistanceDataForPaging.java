package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_AssistanceDataForPaging extends NGAP_Sequence {

    public NGAP_AssistanceDataForRecommendedCells assistanceDataForRecommendedCells;
    public NGAP_PagingAttemptInformation pagingAttemptInformation;

    @Override
    public String getAsnName() {
        return "AssistanceDataForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "AssistanceDataForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"assistanceDataForRecommendedCells", "pagingAttemptInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"assistanceDataForRecommendedCells", "pagingAttemptInformation"};
    }
}
