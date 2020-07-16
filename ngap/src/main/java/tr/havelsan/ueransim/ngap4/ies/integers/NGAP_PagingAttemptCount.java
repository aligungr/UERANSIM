package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_PagingAttemptCount extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "PagingAttemptCount";
    }

    @Override
    protected String getXmlTagName() {
        return "PagingAttemptCount";
    }
}
