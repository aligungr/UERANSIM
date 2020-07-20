package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_PagingAttemptInformation extends NGAP_Sequence {

    public NGAP_PagingAttemptCount pagingAttemptCount;
    public NGAP_IntendedNumberOfPagingAttempts intendedNumberOfPagingAttempts;
    public NGAP_NextPagingAreaScope nextPagingAreaScope;

    @Override
    public String getAsnName() {
        return "PagingAttemptInformation";
    }

    @Override
    public String getXmlTagName() {
        return "PagingAttemptInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }
}
