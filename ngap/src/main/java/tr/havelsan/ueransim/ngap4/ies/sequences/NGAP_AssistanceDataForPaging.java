package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_AssistanceDataForPaging extends NgapSequence {

    public NGAP_AssistanceDataForRecommendedCells assistanceDataForRecommendedCells;
    public NGAP_PagingAttemptInformation pagingAttemptInformation;

    @Override
    protected String getAsnName() {
        return "AssistanceDataForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "AssistanceDataForPaging";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"assistanceDataForRecommendedCells", "pagingAttemptInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"assistanceDataForRecommendedCells", "pagingAttemptInformation"};
    }
}
