package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_NextPagingAreaScope;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_IntendedNumberOfPagingAttempts;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_PagingAttemptCount;

public class NGAP_PagingAttemptInformation extends NgapSequence {

    public NGAP_PagingAttemptCount pagingAttemptCount;
    public NGAP_IntendedNumberOfPagingAttempts intendedNumberOfPagingAttempts;
    public NGAP_NextPagingAreaScope nextPagingAreaScope;

    @Override
    protected String getAsnName() {
        return "PagingAttemptInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "PagingAttemptInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }
}
